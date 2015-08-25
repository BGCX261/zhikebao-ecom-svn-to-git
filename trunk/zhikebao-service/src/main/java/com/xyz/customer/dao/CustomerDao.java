package com.xyz.customer.dao;

import org.springframework.stereotype.Repository;

import com.xyz.customer.model.Customer;
import com.xyz.framework.data.impl.JpaDao;

public class CustomerDao extends JpaDao<Customer, String> implements ICustomerDao {

}
