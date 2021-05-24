package com.supplychain.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.supplychain.beans.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{


	@Query(value="SELECT product_name,product_price from product" ,nativeQuery = true)
	public List<String> getProduct();
	
	@Query(value="SELECT f.product_price from product f where f.product_name=:product_name",nativeQuery = true)
	public double itemPrice(@Param("product_name") String p_Name);
	

	public List<Product> findByproductPrice(double productPrice);
	
	
}
