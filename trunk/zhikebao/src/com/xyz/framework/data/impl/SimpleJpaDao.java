/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SimpleHibernateDao.java,v 1.2 2009/09/27 07:21:01 myc Exp $
 */
package com.xyz.framework.data.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.xyz.framework.data.DataUtil;
import com.xyz.util.ReflectionUtil;

/**
 * 封装JDO原生API的DAO泛型基类.
 * 
 * 可在Service层直接使用,也可以扩展泛型DAO子类使用.
 * 参考Spring2.5自带的Petlinc例子,取消了JDOTemplate,直接使用JDO原生API.
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 * 
 * @author calvin
 */
@SuppressWarnings("unchecked")
public class SimpleJpaDao<T, PK extends Serializable> {

	protected Logger logger = Logger.getLogger(getClass());
	
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
	    this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected Class<T> entityClass;
	
	protected String getBaseSql(){
		return " select e from "+entityClass.getName() +" e ";
	}

	/**
	 * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * SimpleJDODao<User, Long>
	 */
	public SimpleJpaDao() {
		this.entityClass = ReflectionUtil.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用SimpleJDODao的构造函数. 在构造函数中定义对象类型Class. eg.
	 * SimpleJDODao<User, Long> userDao = new SimpleJDODao<User,
	 * Long>(sessionFactory, User.class);
	 */
	public SimpleJpaDao(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 创建一个实体
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		// Transaction tx = getPm().currentTransaction();
		// tx.begin();
		DataUtil.onSave(entity);
		entityManager.persist(entity);
		// tx.commit();
		logger.debug("save entity:" + entity);
	}

	/**
	 * 更新一个实体
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public T update(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		DataUtil.onUpdate(entity);
		
		logger.debug("update entity: " + entity);
		
		return entityManager.merge(entity);
		// tx.commit();
		
	}

	/**
	 * 删除对象.
	 * 
	 * @param entity
	 *            对象必须是session中的对象或含id属性的transient对象.
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		// Transaction tx = getPm().currentTransaction();
		// tx.begin();
		entityManager.remove(entity);
		// tx.commit();
		logger.debug("delete entity: " + entity);
	}

	/**
	 * 按id删除对象.
	 */
	public void delete(final Integer id) {
		Assert.notNull(id, "id不能为空");
		// Transaction tx = getPm().currentTransaction();
		// tx.begin();
		delete(get(id));
		// tx.commit();
		logger.debug("delete entity " + entityClass.getSimpleName() + ",id is "
				+ id);
	}

	/**
	 * 删除所有对象.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void deleteAll() {
		String jpasql = "delete from " + entityClass.getName();
		Query query = entityManager.createQuery(jpasql);
		query.executeUpdate();
		logger.debug("delete all entities " + entityClass.getSimpleName());
	}
	/**
	 * 按id获取对象引用.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T getRef(final Object id)
	{
		Assert.notNull(id, "id不能为空");
		T t = (T) entityManager.getReference(entityClass, id);
		return t;
	}
	/**
	 * 按id获取对象.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T get(final Object id) {
		Assert.notNull(id, "id不能为空");
		T t = (T) entityManager.find(entityClass, id);
		return t;
	}
	/**
	 * 按Long型id获取对象.
	 */
	/*@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T getById(final Long id)
	{
		Assert.notNull(id, "id不能为空");
		Key k = KeyFactory.createKey(entityClass.getSimpleName(), id);
		T t = (T) entityManager.getReference(entityClass, KeyFactory.keyToString(k));
		return t;
	}*/

	/**
	 * 获取全部对象.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> getAll() {
		Query query = entityManager.createQuery(getBaseSql());
		return query.getResultList();
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");

		String sql = getBaseSql() + " where "+propertyName +" = :para ";
		Query query = entityManager.createQuery(sql);
		query.setParameter("para", value);
		return query.getResultList();
		
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T findUniqueBy(final String propertyName, final Object value) {
		T t = null;
		try {
			Assert.hasText(propertyName, "propertyName不能为空");
			String sql = getBaseSql() + " where "+propertyName +" = :para ";
			Query query = entityManager.createQuery(sql);
			query.setParameter("para", value);
			t = (T)query.getSingleResult();
			return t;
		} catch (NoResultException e) {
			logger.info( "没有查询到结果："+entityClass.getName()+":"+propertyName+"="+value);
			return null;
		}
	}

	/**
	 * 按主键Key集合获取对象.
	 *//*
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> findByIds(Set<Key> ids) {
		List<T> lt = new ArrayList<T>();
		for (Key k : ids) {
			T t = entityManager.find(entityClass, k.getId());
			if (t != null) {
				lt.add(t);
			}
		}
		String sql = getBaseSql() + "where key = :keys ";
		Query query = entityManager.createQuery(sql);
		query.setParameter(0, ids);
		return query.getResultList();
	}*/

	/**
	 * 按id列表获取对象.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> findByIds(List<Object> ids) {
		/*List<T> lt = new ArrayList<T>();
		for (Object l : ids) {
			T t = entityManager.find(entityClass, l);
			if (t != null) {
				lt.add(t);
			}
		}
		return lt;*/
		String sql = getBaseSql() + "where key = :keys ";
		Query query = entityManager.createQuery(sql);
		query.setParameter("keys", ids);
		return query.getResultList();
	}

	/**
	 * 按条件查询并排序对象列表.
	 * 
	 * @param
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> find(final String filter, final String orderStr) {
		Assert.hasLength(filter, "查询条件不能为空");
		Assert.hasLength(orderStr, "排序条件不能为空");
		String sql = getBaseSql() +" where "+filter +" order by "+orderStr ;
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}

	/**
	 * 按条件查询对象列表.
	 * 
	 * @param
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public  List<T> find(final String filter) {
		Assert.hasLength(filter, "查询条件不能为空");
		String sql = getBaseSql() +" where "+filter ;
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}

	/**
	 * 按JdoQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <X> List<X> find(final String jpaQl, final Map<String, Object> values) {
		String sql = getBaseSql() + " where "+jpaQl  ;
		Query query = entityManager.createQuery(sql);
		Set<String> paraKeys = values.keySet();
		for(String k : paraKeys)
		{
			Object val = values.get(k);
			query.setParameter(k, val);
		}
		return query.getResultList();
	}

	/**
	 * 按JdoQL查询列表.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <X> List<X> find(final String jpaQl, final Object... values) {
		String sql = getBaseSql() + " where "+jpaQl  ;
		Query query = entityManager.createQuery(sql);
		for(int i=0;i<values.length;i++)
		{
			query.setParameter(i, values[i]);
		}
		
		return query.getResultList();
	}

	/**
	 * 按JdoQL查询唯一对象.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <X> X findUnique(final String jpaQl, final Object... values) {
		String sql = getBaseSql() + " where "+jpaQl  ;
		Query query = entityManager.createQuery(sql);
		for(int i=0;i<values.length;i++)
		{
			query.setParameter(i, values[i]);
		}
		
		return (X)query.getSingleResult();

	}

	/**
	 * 按JdoQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <X> X findUnique(final String jpaQl, final Map<String, Object> values) {
		String sql = getBaseSql() + " where "+jpaQl  ;
		Query query = entityManager.createQuery(sql);
		Set<String> paraKeys = values.keySet();
		for(String k : paraKeys)
		{
			Object val = values.get(k);
			query.setParameter(k, val);
		}
		
		return (X)query.getSingleResult();
	}

	/**
	 * 执行JdoQL进行批量修改/删除操作.
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int batchExecute(final String jdoQL, final Object... values) {
		Query query = entityManager.createQuery(jdoQL);
		for(int i=0;i<values.length;i++)
		{
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate();
	}

	/**
	 * 进行批量修改/删除操作.
	 * 
	 * @return 更新记录数.
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void batchExecute(final List entities) {
		
	}

	/**
	 * 通过Set将不唯一的对象列表唯一化. 主要用于HQL/Criteria预加载关联集合形成重复记录,又不方便使用distinct查询语句时.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <X> List<X> distinct(List list) {
		Set<X> set = new LinkedHashSet<X>(list);
		return new ArrayList<X>(set);
	}

}
