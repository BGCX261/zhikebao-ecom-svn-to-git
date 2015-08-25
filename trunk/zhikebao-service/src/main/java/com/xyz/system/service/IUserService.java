package com.xyz.system.service;

import java.util.List;

import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.util.Page;

/**
 * Local interface for TaobaoUserFacade.
 * 
 * @author sea
 */
public interface IUserService {
	/**
	 * Perform an initial save of a previously unsaved TaobaoUser entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            TaobaoUser entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void saveShop(Shop entity);
	
	/**
	 * 淘宝客户保存
	 * @param entity
	 */
	public void saveUser(com.taobao.api.model.User entity);

	/**
	 * Delete a persistent TaobaoUser entity.
	 * 
	 * @param entity
	 *            TaobaoUser entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Shop entity);

	/**
	 * Persist a previously saved TaobaoUser entity and return it or a copy of
	 * it to the sender. A copy of the TaobaoUser entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            TaobaoUser entity to update
	 * @return TaobaoUser the persisted TaobaoUser entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public void update(Shop entity);

	public Shop findById(Object id);

	/**
	 * Find all TaobaoUser entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TaobaoUser property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TaobaoUser> found by query
	 */
	public List<Shop> findByProperty(String propertyName, Object value, int... rowStartIdxAndCount);

	public List<Shop> findByAppKey(Object appKey, int... rowStartIdxAndCount);

	public List<Shop> findByAppSecret(Object appSecret, int... rowStartIdxAndCount);

	public Shop findByTbAccount(Object tbAccount);

	public List<Shop> findByEmail(Object email, int... rowStartIdxAndCount);

	public List<Shop> findByIsAuthorize(Object isAuthorize, int... rowStartIdxAndCount);

	public List<Shop> findBySessionKey(Object sessionKey, int... rowStartIdxAndCount);
	
	public Shop findByUser(User user);
	/**
	 * 根据昵称查询淘宝用户
	 * @param user
	 * @return
	 */
	public com.taobao.api.model.User findByNick(String nick);
	
	/**
	 * 根据Key查询淘宝用户
	 * @param user
	 * @return
	 */
	public com.taobao.api.model.User findByKey(String key);
	
	public Shop findFromTaobao(Shop user);

	/**
	 * Find all TaobaoUser entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TaobaoUser> all TaobaoUser entities
	 */
	public List<Shop> findAll(int... rowStartIdxAndCount);

	public Shop getAuthorizeShop();
}