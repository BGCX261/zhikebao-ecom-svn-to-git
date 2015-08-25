package com.xyz.framework.data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.PropertyFilter.MatchType;

public interface IDataObject<T, PK extends Serializable> {
	/**
	 * 得到当前的EntityManager
	 * @return
	 */
	public EntityManager getEntityManager();
	/**
	 * 保存新增或修改的对象.
	 */
	public abstract void save(final T entity);

	/**
	 * 删除对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	public abstract void delete(final T entity);
	/**
	 * 更新对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	public T update(final T entity);

	/**
	 * 按id删除对象.
	 */
	public abstract void delete(final Integer id);

	/**
	 * 按id获取对象.
	 */
	public abstract T get(final Object id);
	/**
	 * 按id获取对象引用.
	 */
	public abstract T getRef(final Object id);
	/**
	 * 根据Long型数字 ID查询获取对象.
	 */
	/*public abstract T getById(final Long id);*/

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	public abstract List<T> findBy(final String propertyName, final Object value);

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public abstract T findUniqueBy(final String propertyName, final Object value);
	
/*	*//**
	 * 按id列表获取对象.
	 *//*
	public abstract List<T> findByIds(List<Object> ids);*/

	/**
	 * 按条件查询对象列表.
	 * @param
	 */
	public <X> List<X> find(final String filter, final String orderStr);

	/**
	 * 按SQL查询对象列表.
	 * 包括jpql\hql\jdoql
	 * @param values 命名参数,按名称绑定.
	 */
	public abstract <X> List<X> find(final String sql,
			final Map<String, Object> values);

	/**
	 * 按SQL查询唯一对象.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public abstract <X> X findUnique(final String sql, final Object... values);

	/**
	 * 按SQL查询唯一对象.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	public abstract <X> X findUnique(final String sql,
			final Map<String, Object> values);

	/**
	 * 批量修改/删除操作.
	 */
	public abstract void batchExecute(final List entities);

	/**
	 * 通过Set将不唯一的对象列表唯一化.
	 * 主要用于JdoQL/Criteria预加载关联集合形成重复记录,又不方便使用distinct查询语句时.
	 */
	public abstract <X> List<X> distinct(List list);

	/**
	 * 按JDOQL分页查询.
	 * 
	 * @param page 分页参数.不支持其中的orderBy参数.
	 * @param JDOQL JDOQL 语句.
	 * @param values 数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public abstract Page<T> findPage(final Page<T> page, final String sql);
	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType 匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
	 */
	public abstract List<T> findBy(final String propertyName,
			final Object value, final MatchType matchType);

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public abstract List<T> find(List<PropertyFilter> filters);
	
	public <X> List<X> find(final String JdoQL, final Object... values);

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	
	public abstract Page<T> findPage(final Page<T> page,
			final List<PropertyFilter> filters);

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public abstract boolean isPropertyUnique(final String propertyName,
			final Object newValue, final Object oldValue);
	/**
	 * 查询一个表中所有数据
	 * @return list
	 */
	public List<T> getAll();
	
	/**
	 * 删除所有对象.
	 */
	public void deleteAll() ;

	/**
	 * 分页获取全部对象.
	 */
	public Page<T> getAll(final Page<T> page);

	/**
	 * 分页查询.
	 * 
	 * @param page
	 *            分页参数.不支持其中的orderBy参数.
	 * @param jdoQl
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page);

	/**
	 * 按属性分页查找对象列表,匹配方式为相等.
	 */
	public Page<T> findBy(final Page page, final String propertyName, final Object value);

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的jpaQl语句,复杂的jpaQl查询请另行编写count语句查询.
	 */
	public int countJpaQlResult(final String jpaQl);

}