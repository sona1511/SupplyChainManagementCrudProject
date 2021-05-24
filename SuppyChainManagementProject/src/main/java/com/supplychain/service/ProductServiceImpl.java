package com.supplychain.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplychain.beans.Customer;
import com.supplychain.beans.Order;
import com.supplychain.repository.CustomerRepository;
import com.supplychain.repository.OrderRepository;
import com.supplychain.repository.ProductRepository;


@Transactional
@Service
public class ProductServiceImpl implements ProductService{

	HashMap<String,Double> selectedProduct = new HashMap<String,Double>();
	@Autowired
	ProductRepository mRepo;
	
	
	@Autowired
	OrderRepository oRepo;
	
	@Autowired
	CustomerRepository cRepo;

	@Override
	public List<String> getProduct() {
		List<String> product = mRepo.getProduct();
		return product;
	}



}
