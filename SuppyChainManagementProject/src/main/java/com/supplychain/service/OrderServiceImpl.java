package com.supplychain.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplychain.beans.Customer;
import com.supplychain.beans.Order;
import com.supplychain.beans.Product;
import com.supplychain.repository.CustomerRepository;
import com.supplychain.repository.OrderRepository;
import com.supplychain.repository.ProductRepository;

@Transactional
@Service
public class OrderServiceImpl implements OrderService{

	static HashMap<String,Double> data = new HashMap<String,Double>();
	@Autowired
	OrderRepository oRepo;
	
	
	@Override
	public HashMap<String,Double> addOrder(Order order) {
		oRepo.saveAndFlush(order);
		
		return null;
		
	}
	
}
