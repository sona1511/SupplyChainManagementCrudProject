package com.supplychain.service;

import java.util.HashMap;

import com.supplychain.beans.Order;
import com.supplychain.beans.Product;

public interface OrderService {

	public HashMap<String,Double> addOrder(Order order);
	
	
}