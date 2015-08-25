package com.xyz.base.service;

import java.util.List;

import com.xyz.base.model.BaseCode;
import com.xyz.util.Page;

public interface IBaseService {
	
	/**
	 * Perform an initial save of a previously unsaved BossiniOrder entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            BossiniOrder entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	
	public BaseCode save(BaseCode entity);

	/**
	 * Delete a persistent BossiniOrder entity.
	 * 
	 * @param entity
	 *            BossiniOrder entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(BaseCode entity);

	/**
	 * Persist a previously saved BossiniOrder entity and return it or a copy of
	 * it to the sender. A copy of the BossiniOrder entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 */
	public void update(BaseCode entity);
	/**
	 * 根据Long型数字 ID查询
	 * @param id
	 * @return
	 */
	public BaseCode findByKeyId(Long id);
	/**
	 * 根据字符串 ID查询
	 * @param id
	 * @return
	 */
	public BaseCode findById(String id);

	/**
	 * Find all BossiniOrder entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BossiniOrder property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<BossiniOrder> found by query
	 */
	public Page<BaseCode> findByProperty(String propertyName, Object value, Page page);

	
	/**
	 * Find all entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<BossiniOrder> all BossiniOrder entities
	 */
	public Page<BaseCode> findAll(Page page);

	/**
	 * 根据父类别的ID得到代码列表
	 * @param parentId
	 * @return
	 */
	public List<BaseCode> queryByType(Long parentId);

}
