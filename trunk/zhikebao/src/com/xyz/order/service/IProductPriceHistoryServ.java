package com.xyz.order.service;

import java.util.List;
import com.xyz.order.model.ProductPriceHistory;

/**
 * Local interface for ProductPriceHistoryFacade.
 * 
 * @author sea
 */
public interface IProductPriceHistoryServ {
	public void save(ProductPriceHistory entity);

	public void delete(ProductPriceHistory entity);

	public ProductPriceHistory update(ProductPriceHistory entity);

	public ProductPriceHistory findById(Long id);

	public List<ProductPriceHistory> findByProperty(String propertyName, Object value, int... rowStartIdxAndCount);

	public List<ProductPriceHistory> findByProductKey(Object productKey, int... rowStartIdxAndCount);

	public List<ProductPriceHistory> findByPrice(Object price, int... rowStartIdxAndCount);

	public List<ProductPriceHistory> findByTbiid(Object tbiid, int... rowStartIdxAndCount);

	public List<ProductPriceHistory> findByIsSuccess(Object isSuccess, int... rowStartIdxAndCount);

	public List<ProductPriceHistory> findByErrorMsg(Object errorMsg, int... rowStartIdxAndCount);

	public List<ProductPriceHistory> findAll(int... rowStartIdxAndCount);
}