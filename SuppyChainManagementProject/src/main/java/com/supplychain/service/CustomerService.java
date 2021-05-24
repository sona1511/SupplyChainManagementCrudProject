package com.supplychain.service;

import java.util.HashMap;
import java.util.List;

import com.supplychain.beans.Customer;

public interface CustomerService {

String saveOrUpdate(Customer customer);
	
	List<Customer> getAllCustomers();
	
	Customer loginCustomer(int customerId, String password);
	
	Customer findOneById(int customerId);
}
