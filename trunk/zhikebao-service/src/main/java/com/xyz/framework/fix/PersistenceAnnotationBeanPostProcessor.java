package com.xyz.framework.fix;

/*
 GAE: Spring JPA: Needed to get around the "java.lang.NoClassDefFoundError:
 * javax/naming/NamingException" blacklisting problem.
 * 
 * Just register this dummy impl as bean with id:
 */

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceProperty;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerProxy;
import org.springframework.orm.jpa.ExtendedEntityManagerCreator;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

/**
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 2.0
 * @see javax.persistence.PersistenceUnit
 * @see javax.persistence.PersistenceContext
 */
public class PersistenceAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor,
    DestructionAwareBeanPostProcessor,MergedBeanDefinitionPostProcessor, PriorityOrdered, BeanFactoryAware, Serializable {
	
	private transient Map<String, String> persistenceUnits;

	private transient Map<String, String> persistenceContexts;

	private transient Map<String, String> extendedPersistenceContexts;

	private transient String defaultPersistenceUnitName = "transactions-optional";

	private int order = Ordered.LOWEST_PRECEDENCE - 4;

	private transient ListableBeanFactory beanFactory;

	private transient final Map<Class<?>, InjectionMetadata> injectionMetadataCache =
			new ConcurrentHashMap<Class<?>, InjectionMetadata>();

	private final Map<Object, EntityManager> extendedEntityManagersToClose =
			new ConcurrentHashMap<Object, EntityManager>();


	public PersistenceAnnotationBeanPostProcessor() {
		}


	/**
	 * Specify the persistence units for EntityManagerFactory lookups,
	 * as a Map from persistence unit name to persistence unit JNDI name
	 * (which needs to resolve to an EntityManagerFactory instance).
	 * <p>JNDI names specified here should refer to <code>persistence-unit-ref</code>
	 * entries in the Java EE deployment descriptor, matching the target persistence unit.
	 * <p>In case of no unit name specified in the annotation, the specified value
	 * for the {@link #setDefaultPersistenceUnitName default persistence unit}
	 * will be taken (by default, the value mapped to the empty String),
	 * or simply the single persistence unit if there is only one.
	 * <p>This is mainly intended for use in a Java EE 5 environment, with all
	 * lookup driven by the standard JPA annotations, and all EntityManagerFactory
	 * references obtained from JNDI. No separate EntityManagerFactory bean
	 * definitions are necessary in such a scenario.
	 * <p>If no corresponding "persistenceContexts"/"extendedPersistenceContexts"
	 * are specified, <code>@PersistenceContext</code> will be resolved to
	 * EntityManagers built on top of the EntityManagerFactory defined here.
	 * Note that those will be Spring-managed EntityManagers, which implement
	 * transaction synchronization based on Spring's facilities.
	 * If you prefer the Java EE 5 server's own EntityManager handling,
	 * specify corresponding "persistenceContexts"/"extendedPersistenceContexts".
	 */
	public void setPersistenceUnits(Map<String, String> persistenceUnits) {
		this.persistenceUnits = persistenceUnits;
	}

	/**
	 * Specify the <i>transactional</i> persistence contexts for EntityManager lookups,
	 * as a Map from persistence unit name to persistence context JNDI name
	 * (which needs to resolve to an EntityManager instance).
	 * <p>JNDI names specified here should refer to <code>persistence-context-ref</code>
	 * entries in the Java EE deployment descriptors, matching the target persistence unit
	 * and being set up with persistence context type <code>Transaction</code>.
	 * <p>In case of no unit name specified in the annotation, the specified value
	 * for the {@link #setDefaultPersistenceUnitName default persistence unit}
	 * will be taken (by default, the value mapped to the empty String),
	 * or simply the single persistence unit if there is only one.
	 * <p>This is mainly intended for use in a Java EE 5 environment, with all
	 * lookup driven by the standard JPA annotations, and all EntityManager
	 * references obtained from JNDI. No separate EntityManagerFactory bean
	 * definitions are necessary in such a scenario, and all EntityManager
	 * handling is done by the Java EE 5 server itself.
	 */
	public void setPersistenceContexts(Map<String, String> persistenceContexts) {
		this.persistenceContexts = persistenceContexts;
	}

	/**
	 * Specify the <i>extended</i> persistence contexts for EntityManager lookups,
	 * as a Map from persistence unit name to persistence context JNDI name
	 * (which needs to resolve to an EntityManager instance).
	 * <p>JNDI names specified here should refer to <code>persistence-context-ref</code>
	 * entries in the Java EE deployment descriptors, matching the target persistence unit
	 * and being set up with persistence context type <code>Extended</code>.
	 * <p>In case of no unit name specified in the annotation, the specified value
	 * for the {@link #setDefaultPersistenceUnitName default persistence unit}
	 * will be taken (by default, the value mapped to the empty String),
	 * or simply the single persistence unit if there is only one.
	 * <p>This is mainly intended for use in a Java EE 5 environment, with all
	 * lookup driven by the standard JPA annotations, and all EntityManager
	 * references obtained from JNDI. No separate EntityManagerFactory bean
	 * definitions are necessary in such a scenario, and all EntityManager
	 * handling is done by the Java EE 5 server itself.
	 */
	public void setExtendedPersistenceContexts(Map<String, String> extendedPersistenceContexts) {
		this.extendedPersistenceContexts = extendedPersistenceContexts;
	}

	/**
	 * Specify the default persistence unit name, to be used in case
	 * of no unit name specified in an <code>@PersistenceUnit</code> /
	 * <code>@PersistenceContext</code> annotation.
	 * <p>This is mainly intended for lookups in the application context,
	 * indicating the target persistence unit name (typically matching
	 * the bean name), but also applies to lookups in the
	 * {@link #setPersistenceUnits "persistenceUnits"} /
	 * {@link #setPersistenceContexts "persistenceContexts"} /
	 * {@link #setExtendedPersistenceContexts "extendedPersistenceContexts"} map,
	 * avoiding the need for duplicated mappings for the empty String there.
	 * <p>Default is to check for a single EntityManagerFactory bean
	 * in the Spring application context, if any. If there are multiple
	 * such factories, either specify this default persistence unit name
	 * or explicitly refer to named persistence units in your annotations.
	 */
	public void setDefaultPersistenceUnitName(String unitName) {
		this.defaultPersistenceUnitName = (unitName != null ? unitName : "");
	}

	public void setOrder(int order) {
	  this.order = order;
	}

	public int getOrder() {
	  return this.order;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		if (beanFactory instanceof ListableBeanFactory) {
			this.beanFactory = (ListableBeanFactory) beanFactory;
		}
	}


	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class beanType, String beanName) {
		if (beanType != null) {
			InjectionMetadata metadata = findPersistenceMetadata(beanType);
			metadata.checkConfigMembers(beanDefinition);
		}
	}

	public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
		return null;
	}

	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		InjectionMetadata metadata = findPersistenceMetadata(bean.getClass());
		try {
			metadata.injectFields(bean, beanName);
		}
		catch (Throwable ex) {
			throw new BeanCreationException(beanName, "Injection of persistence fields failed", ex);
		}
		return true;
	}

	public PropertyValues postProcessPropertyValues(
			PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {

		InjectionMetadata metadata = findPersistenceMetadata(bean.getClass());
		try {
			metadata.injectMethods(bean, beanName, pvs);
		}
		catch (Throwable ex) {
			throw new BeanCreationException(beanName, "Injection of persistence methods failed", ex);
		}
		return pvs;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
		EntityManager emToClose = this.extendedEntityManagersToClose.remove(bean);
		EntityManagerFactoryUtils.closeEntityManager(emToClose);
	}


	private InjectionMetadata findPersistenceMetadata(final Class clazz) {
		// Quick check on the concurrent map first, with minimal locking.
		InjectionMetadata metadata = this.injectionMetadataCache.get(clazz);
		if (metadata == null) {
			synchronized (this.injectionMetadataCache) {
				metadata = this.injectionMetadataCache.get(clazz);
				if (metadata == null) {
					final InjectionMetadata newMetadata = new InjectionMetadata(clazz);
					ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
						public void doWith(Field field) {
							PersistenceContext pc = field.getAnnotation(PersistenceContext.class);
							PersistenceUnit pu = field.getAnnotation(PersistenceUnit.class);
							if (pc != null || pu != null) {
								if (Modifier.isStatic(field.getModifiers())) {
									throw new IllegalStateException("Persistence annotations are not supported on static fields");
								}
								newMetadata.addInjectedField(new PersistenceElement(field, null));
							}
						}
					});
					ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {
						public void doWith(Method method) {
							PersistenceContext pc = method.getAnnotation(PersistenceContext.class);
							PersistenceUnit pu = method.getAnnotation(PersistenceUnit.class);
							if (pc != null || pu != null &&
									method.equals(ClassUtils.getMostSpecificMethod(method, clazz))) {
								if (Modifier.isStatic(method.getModifiers())) {
									throw new IllegalStateException("Persistence annotations are not supported on static methods");
								}
								if (method.getParameterTypes().length != 1) {
									throw new IllegalStateException("Persistence annotation requires a single-arg method: " + method);
								}
								PropertyDescriptor pd = BeanUtils.findPropertyForMethod(method);
								newMetadata.addInjectedMethod(new PersistenceElement(method, pd));
							}
						}
					});
					metadata = newMetadata;
					this.injectionMetadataCache.put(clazz, metadata);
				}
			}
		}
		return metadata;
	}
    /**
	 * Return a specified persistence unit for the given unit name,
	 * as defined through the "persistenceUnits" map.
	 * @param unitName the name of the persistence unit
	 * @return the corresponding EntityManagerFactory,
	 * or <code>null</code> if none found
	 * @see #setPersistenceUnits
	 */
	protected EntityManagerFactory getPersistenceUnit(String unitName) {
		return null;
	}
	
	protected EntityManager getPersistenceContext(String unitName, boolean extended) {
		/*
		Assert.notNull(entityManagerFactory, "No EntityManagerFactory specified");
		EntityManager em = null;
		EntityManagerHolder emHolder =
				(EntityManagerHolder) TransactionSynchronizationManager.getResource(entityManagerFactory);
		if (emHolder != null) {
			if (!emHolder.isSynchronizedWithTransaction() &&
					TransactionSynchronizationManager.isSynchronizationActive()) {
				// Try to explicitly synchronize the EntityManager itself
				// with an ongoing JTA transaction, if any.
				try {
					em = emHolder.getEntityManager();
					
				}
				catch (TransactionRequiredException ex) {
					logger.debug("Could not join JTA transaction because none was active", ex);
					em = entityManagerFactory.createEntityManager();
				}
	        }
		}else{
			em = entityManagerFactory.createEntityManager();
			TransactionSynchronizationManager.bindResource(entityManagerFactory, new EntityManagerHolder(em));
		}*/
		return null;
	}

	/**
	 * Find an EntityManagerFactory with the given name in the current Spring
	 * application context, falling back to a single default EntityManagerFactory
	 * (if any) in case of no unit name specified.
	 * @param unitName the name of the persistence unit (may be <code>null</code> or empty)
	 * @param requestingBeanName the name of the requesting bean
	 * @return the EntityManagerFactory
	 * @throws NoSuchBeanDefinitionException if there is no such EntityManagerFactory in the context
	 */
	protected EntityManagerFactory findEntityManagerFactory(String unitName, String requestingBeanName)
			throws NoSuchBeanDefinitionException {

		if (this.beanFactory == null) {
			throw new IllegalStateException("ListableBeanFactory required for EntityManagerFactory bean lookup");
		}
		String unitNameForLookup = (unitName != null ? unitName : "");
		if ("".equals(unitNameForLookup)) {
			unitNameForLookup = this.defaultPersistenceUnitName;
		}
		if (!"".equals(unitNameForLookup)) {
			return findNamedEntityManagerFactory(unitNameForLookup, requestingBeanName);
		}
		else {
			return findDefaultEntityManagerFactory(requestingBeanName);
		}
	}

	/**
	 * Find an EntityManagerFactory with the given name in the current
	 * Spring application context.
	 * @param unitName the name of the persistence unit (never empty)
	 * @param requestingBeanName the name of the requesting bean
	 * @return the EntityManagerFactory
	 * @throws NoSuchBeanDefinitionException if there is no such EntityManagerFactory in the context
	 */
	protected EntityManagerFactory findNamedEntityManagerFactory(String unitName, String requestingBeanName)
			throws NoSuchBeanDefinitionException {

		EntityManagerFactory emf = EntityManagerFactoryUtils.findEntityManagerFactory(this.beanFactory, unitName);
		if (this.beanFactory instanceof ConfigurableBeanFactory) {
			((ConfigurableBeanFactory) this.beanFactory).registerDependentBean(unitName, requestingBeanName);
		}
		return emf;
	}

	/**
	 * Find a single default EntityManagerFactory in the Spring application context.
	 * @return the default EntityManagerFactory
	 * @throws NoSuchBeanDefinitionException if there is no single EntityManagerFactory in the context
	 */
	protected EntityManagerFactory findDefaultEntityManagerFactory(String requestingBeanName)
			throws NoSuchBeanDefinitionException{

		String[] beanNames =
				BeanFactoryUtils.beanNamesForTypeIncludingAncestors(this.beanFactory, EntityManagerFactory.class);
		if (beanNames.length == 1) {
			String unitName = beanNames[0];
			EntityManagerFactory emf = (EntityManagerFactory) this.beanFactory.getBean(unitName);
			if (this.beanFactory instanceof ConfigurableBeanFactory) {
				((ConfigurableBeanFactory) this.beanFactory).registerDependentBean(unitName, requestingBeanName);
			}
			return emf;
		}
		else {
			throw new NoSuchBeanDefinitionException(
					EntityManagerFactory.class, "expected single bean but found " + beanNames.length);
		}
	}


	/**
	 * Class representing injection information about an annotated field
	 * or setter method.
	 */
	private class PersistenceElement extends InjectionMetadata.InjectedElement {

		private final String unitName;

		private PersistenceContextType type;

		private Properties properties;

		public PersistenceElement(Member member, PropertyDescriptor pd) {
			super(member, pd);
			AnnotatedElement ae = (AnnotatedElement) member;
			PersistenceContext pc = ae.getAnnotation(PersistenceContext.class);
			PersistenceUnit pu = ae.getAnnotation(PersistenceUnit.class);
			Class resourceType = EntityManager.class;
			if (pc != null) {
				if (pu != null) {
					throw new IllegalStateException("Member may only be annotated with either " +
							"@PersistenceContext or @PersistenceUnit, not both: " + member);
				}
				Properties properties = null;
				PersistenceProperty[] pps = pc.properties();
				if (!ObjectUtils.isEmpty(pps)) {
					properties = new Properties();
					for (PersistenceProperty pp : pps) {
						properties.setProperty(pp.name(), pp.value());
					}
				}
				this.unitName = pc.unitName();
				this.type = pc.type();
				this.properties = properties;
			}
			else {
				resourceType = EntityManagerFactory.class;
				this.unitName = pu.unitName();
			}
			checkResourceType(resourceType);
		}

		/**
		 * Resolve the object against the application context.
		 */
		@Override
		protected Object getResourceToInject(Object target, String requestingBeanName) {
			// Resolves to EntityManagerFactory or EntityManager.
			if (this.type != null) {
				return (this.type == PersistenceContextType.EXTENDED ?
						resolveExtendedEntityManager(target, requestingBeanName) :
						resolveEntityManager(requestingBeanName));
			}
			else {
				// OK, so we need an EntityManagerFactory...
				return resolveEntityManagerFactory(requestingBeanName);
			}
		}

		private EntityManagerFactory resolveEntityManagerFactory(String requestingBeanName) {
			// Obtain EntityManagerFactory from JNDI?
			EntityManagerFactory emf = getPersistenceUnit(this.unitName);
			if (emf == null) {
				// Need to search for EntityManagerFactory beans.
				emf = findEntityManagerFactory(this.unitName, requestingBeanName);
			}
			return emf;
		}

		private EntityManager resolveEntityManager(String requestingBeanName) {
			// Obtain EntityManager reference from JNDI?
			EntityManager em = getPersistenceContext(this.unitName, false);
			if (em == null) {
				// No pre-built EntityManager found -> build one based on factory.
				// Obtain EntityManagerFactory from JNDI?
				EntityManagerFactory emf = getPersistenceUnit(this.unitName);
				if (emf == null) {
					// Need to search for EntityManagerFactory beans.
					emf = findEntityManagerFactory(this.unitName, requestingBeanName);
				}
				// Inject a shared transactional EntityManager proxy.
				if (emf instanceof EntityManagerFactoryInfo &&
						((EntityManagerFactoryInfo) emf).getEntityManagerInterface() != null) {
					// Create EntityManager based on the info's vendor-specific type
					// (which might be more specific than the field's type).
					em = SharedEntityManagerCreator.createSharedEntityManager(emf, this.properties);
				}
				else {
					// Create EntityManager based on the field's type.
					em = SharedEntityManagerCreator.createSharedEntityManager(emf, this.properties, getResourceType());
				}
			}
			return em;
		}

		private EntityManager resolveExtendedEntityManager(Object target, String requestingBeanName) {
			// Obtain EntityManager reference from JNDI?
			EntityManager em = getPersistenceContext(this.unitName, true);
			if (em == null) {
				// No pre-built EntityManager found -> build one based on factory.
				// Obtain EntityManagerFactory from JNDI?
				EntityManagerFactory emf = getPersistenceUnit(this.unitName);
				if (emf == null) {
					// Need to search for EntityManagerFactory beans.
					emf = findEntityManagerFactory(this.unitName, requestingBeanName);
				}
				// Inject a container-managed extended EntityManager.
				em = ExtendedEntityManagerCreator.createContainerManagedEntityManager(emf, this.properties);
			}
			if (em instanceof EntityManagerProxy &&
					beanFactory != null && !beanFactory.isPrototype(requestingBeanName)) {
				extendedEntityManagersToClose.put(target, ((EntityManagerProxy) em).getTargetEntityManager());
			}
			return em;
		}
	}

}
