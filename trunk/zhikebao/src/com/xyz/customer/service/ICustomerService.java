package com.xyz.customer.service;

import java.util.List;
import java.util.Set;

import com.xyz.customer.model.Customer;
import com.xyz.customer.model.Guest;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

public interface ICustomerService {

	/**
	 * 保存
	 */
	public void save(Customer entity);
	
	/**
	 * 保存访客信息(潜在客户)
	 */
	public void save(Guest guest);
	/**
	 * 分页查询访客(潜在客户)
	 * @param page
	 * @return
	 */
	public Page<Guest> queryGuests(Page<Guest> page);

	/**
	 * 
	 */
	public void delete(String key);

	/**
	 * 
	 */
	public Customer update(Customer entity);

	public Customer findById(String id);

	/**
	 */
	@SuppressWarnings("unchecked")
	public Page<Customer> findByProperty(String propertyName, final Object value, Page p);
	
    /**
     * 根据客户状态查询客户
     * @param status
     * @param page
     * @return
     */
	public Page<Customer> findByStatus(Object status,Page<Customer> page);
	
	/**
	 * 分页查询指定卖家的客户列表
	 * @param page
	 * @return
	 */
	public Page<Customer> queryPage(Page<Customer> page, List<PropertyFilter> filters);

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Page<Customer> findAll(Page<Customer> page);
	
	/**
	 * 根据淘宝帐号查询客户
	 * @param nick
	 * @return
	 */
	public Customer findByShopAndUser(Integer shopKey,Integer userKey);

	@SuppressWarnings("unchecked")
	public Page<Customer> query(Page<Customer> page, String sqlString);

	public Customer querySingle(String sqlString);

	@SuppressWarnings("unchecked")
	public List<Object> query(String sqlString);

	@SuppressWarnings("unchecked")
	public Page<Customer> findManyByProperty(Object[] propertyName, Object[] value, Page page);
	/**
	 * 根据昵称提取用户信息并创建客户
	 */
	public void drawCoustomer(Set<String> nicks);
}