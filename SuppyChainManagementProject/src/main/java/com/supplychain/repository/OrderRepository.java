package com.supplychain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.supplychain.beans.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

	@Query(value="SELECT * from view_order v where v.customer_id=:customer_id" ,nativeQuery = true)
	public List<String> getOrderList(@Param("customer_id") int customer_id);
	
	
}
