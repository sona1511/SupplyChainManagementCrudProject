package com.supplychain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplychain.beans.Customer;
import com.supplychain.repository.CustomerRepository;
import com.supplychain.repository.ProductRepository;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{

		@Autowired
	 	CustomerRepository cRepo;

		@Override
		public String saveOrUpdate(Customer customer) {
			cRepo.saveAndFlush(customer);
			return "customer Registered successfully";	}

		@Override
		public List<Customer> getAllCustomers() {
			List<Customer> customer = new ArrayList<Customer>();
			cRepo.findAll().forEach(u1 -> customer.add(u1));
	       	return customer;
		}

		@Override
		public Customer loginCustomer(int customerId, String password) {
			return cRepo.loginCustomer(customerId, password);

		}

		@Override
		public Customer findOneById(int customerId) {
			return cRepo.findByCustomerId(customerId);
		}


	}


	


