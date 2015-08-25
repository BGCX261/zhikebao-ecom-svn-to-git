package com.xyz.order.service;

import java.util.List;

import com.xyz.order.model.AddressDatabase;

public interface IAddressServ {

	/**
	 * Perform an initial save of a previously unsaved AddressDatabase entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            AddressDatabase entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public abstract void save(AddressDatabase entity);

	/**
	 * Delete a persistent AddressDatabase entity.
	 * 
	 * @param entity
	 *            AddressDatabase entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public abstract void delete(AddressDatabase entity);

	/**
	 * Persist a previously saved AddressDatabase entity and return it or a copy
	 * of it to the sender. A copy of the AddressDatabase entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity.
	 * 
	 * @param entity
	 *            AddressDatabase entity to update
	 * @return AddressDatabase the persisted AddressDatabase entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public abstract AddressDatabase update(AddressDatabase entity);

	public abstract AddressDatabase findById(Integer id);

	/**
	 * Find all AddressDatabase entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the AddressDatabase property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<AddressDatabase> found by query
	 */
	@SuppressWarnings("unchecked")
	public abstract List<AddressDatabase> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByAreaid(Object areaid,
			int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByState(Object state,
			int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByCity(Object city,
			int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByDistrict(Object district,
			int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByAddress(Object address,
			int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByZip(Object zip,
			int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByMobile(Object mobile,
			int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByPhone(Object phone,
			int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByName(Object name,
			int... rowStartIdxAndCount);

	public abstract List<AddressDatabase> findByIsDefault(Object isDefault,
			int... rowStartIdxAndCount);

	/**
	 * Find all AddressDatabase entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<AddressDatabase> all AddressDatabase entities
	 */
	@SuppressWarnings("unchecked")
	public abstract List<AddressDatabase> findAll(
			final int... rowStartIdxAndCount);

}