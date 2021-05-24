package com.supplychain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.supplychain.beans.Admin;
import com.supplychain.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	
	Customer findByCustomerId(int customerId);
	
	@Query("select c from Customer c where c.customerId=:customerId AND c.password=:password")
	public Customer loginCustomer(@Param("customerId") int customerId,@Param("password") String password);

	public Customer findBymailId(String mailId);


}



	


