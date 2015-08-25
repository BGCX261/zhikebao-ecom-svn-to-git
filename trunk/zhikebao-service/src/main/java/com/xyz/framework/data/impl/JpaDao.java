/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: HibernateDao.java,v 1.2 2009/09/25 06:08:05 test Exp $
 */
package com.xyz.framework.data.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.xyz.framework.data.DataUtil;
import com.xyz.framework.data.IDataObject;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.ReflectionUtil;
import com.xyz.util.PropertyFilter.MatchType;

/**
 * 封装扩展功能的Jdo DAO泛型基类.
 * 
 * 扩展功能包括分页查询,按属性过滤条件列表查询. 可在Service层直接使用,也可以扩展泛型DAO子类使用,见两个构造函数的注释.
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 * 
 * @author calvin
 */
public class JpaDao<T, PK extends Serializable> extends SimpleJpaDao<T, PK>
		implements IDataObject<T, PK> {
	/**
	 * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * HibernateDao<User, Long>{ }
	 */
	public JpaDao() {
		super();
	}

	/**
	 * 用于注入实体类型
	 * 
	 * @param entityClass
	 */

	public JpaDao(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	// 分页查询函数 //

	/**
	 * 分页获取全部对象.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<T> getAll(final Page<T> page) {
		return findPage(page, "");
	}

	/**
	 * 按jdoQL分页查询.
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
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<T> findPage(final Page<T> page, final String jpaQl) {
		Assert.notNull(page, "page不能为空");
		String sql = getBaseSql();
		if (jpaQl != null && jpaQl.trim().length() > 0)
			sql += " where " + jpaQl;
		sql = dealOrder(sql,page);
		Query query = entityManager.createQuery(sql);
		if (page.isAutoCount()) {
			int totalCount = countJpaQlResult(jpaQl);
			page.setTotalCount(totalCount);
		}
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getLimit());
		List<T> lt = query.getResultList();
        page.setResult(lt);
		return page;
	}

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
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<T> findPage(final Page<T> page) {
		Assert.notNull(page, "page不能为空");
		String sql = getBaseSql();
		sql = dealOrder(sql,page);
		Query query = entityManager.createQuery(sql);
		if (page.isAutoCount()) {
			int totalCount = countJpaQlResult("");
			page.setTotalCount(totalCount);
		}
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getLimit());
		List<T> lt = query.getResultList();
		page.setResult(lt);
		return page;
	}

	/**
	 * 按属性分页查找对象列表,匹配方式为相等.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<T> findBy(final Page page, final String propertyName,
			final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Assert.notNull(value, "value不能为空");
		final String filter = buildPropertyFilterCriterion(propertyName, value,
				MatchType.EQ);
		String sql = getBaseSql() + " where " + filter;
		sql = dealOrder(sql,page);
		Query query = entityManager.createQuery(sql);
		if (page.isAutoCount()) {
			int totalCount = countJpaQlResult(filter);
			page.setTotalCount(totalCount);
		}
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getLimit());
		List<T> lt = query.getResultList();
		page.setResult(lt);
        return page;
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的jpaQl语句,复杂的jpaQl查询请另行编写count语句查询.
	 */
	public int countJpaQlResult(final String jpaQl) {

		String sql = " select count(1) from " + entityClass.getName() + " e ";
		if (jpaQl != null && jpaQl.trim().length() > 0)
			sql += " where " + jpaQl;
		String countQl = sql;
		int oi = sql.indexOf("order");
		if (oi > 0)
			countQl = sql.substring(0, oi);

		if (countQl.trim().length() > 0) {
			Query query = entityManager.createQuery(countQl);
			return (Integer) query.getSingleResult();
		} else {
			return 0;
		}
	}

	// 属性过滤条件查询函数 //

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType
	 *            匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> findBy(final String propertyName, final Object value,
			final MatchType matchType) {
		String criterion = buildPropertyFilterCriterion(propertyName, value,
				matchType);
		return find(criterion);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> find(List<PropertyFilter> filters) {
		String criterions = buildPropertyFilterCriterions(filters);
		return find(criterions);
	}

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<T> findPage(final Page<T> page,
			final List<PropertyFilter> filters) {
		
		String criterions = buildPropertyFilterCriterions(filters);
		return findPage(page, criterions);
	}

	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	public String buildPropertyFilterCriterions(
			final List<PropertyFilter> filters) {
		String fs = "";
		for (PropertyFilter filter : filters) {
			if (!filter.isMultiProperty()) { // 只有一个属性需要比较的情况.
				fs += buildPropertyFilterCriterion(filter.getPropertyName(),
						filter.getValue(), filter.getMatchType());
			} else {// 包含多个属性需要比较的情况,进行 && 处理.
               for (String param : filter.getPropertyNames()) {
					String criterion = buildPropertyFilterCriterion(param,
							filter.getValue(), filter.getMatchType());
					fs += criterion + " and ";
				}
			}
			fs += " and ";
		}
		int i = fs.lastIndexOf("and");
		if (i > 0)
			fs = fs.substring(0, i);
		return fs;
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	public String buildPropertyFilterCriterion(String propertyName,
			Object value, final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		String criterion = null;
		try {
			// 按entity property中的类型将字符串转化为实际类型.
			/*Object realValue = ReflectionUtil.convertValue(value, entityClass,
					propertyName);*/
			// 对不同类型的值作处理
			if (value instanceof String) {
				String str = (String) value;
				value = DataUtil.toSqlStr(str);
			}

			// 根据MatchType构造criterion
			if (MatchType.EQ.equals(matchType)) {
				criterion = propertyName + " = " + value;
			}
			if (MatchType.LIKE.equals(matchType)) {
				criterion = propertyName + " like '%" + value + "%'";
			}
			if (MatchType.LE.equals(matchType)) {
				criterion = propertyName + " <= " + value;
			}
			if (MatchType.LT.equals(matchType)) {
				criterion = propertyName + " < " + value;
			}
			if (MatchType.GE.equals(matchType)) {
				criterion = propertyName + " >= " + value;
			}
			if (MatchType.GT.equals(matchType)) {
				criterion = propertyName + " > " + value;
			}
		} catch (Exception e) {
			throw ReflectionUtil.convertToUncheckedException(e);
		}
		return criterion;
	}
	/**
	 * 处理排序
	 * @param page
	 * @return
	 */
	private String dealOrder(String jpaQl ,Page<T> page)
	{
		if (page.isOrderBySetted()) {
			jpaQl += " order by "+ page.getOrderBy();
		}
		return jpaQl;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName,
			final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue))
			return true;
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}

}
