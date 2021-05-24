package com.supply.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.annotation.Rollback;

import com.supplychain.beans.Customer;
import com.supplychain.repository.CustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository crepo;
	
 
    @Test
	@DisplayName("Testcase to add new customer in database.")
	@Rollback(false)
    @Order(1)
	public void testAddCustomer() {
		Customer cust = new Customer(1,"Nisha","Chaturvedi","Nisha@8080","nishachat8080@gmail.com","9304300823","Mumbai");
		Customer savedCustomer = crepo.save(cust);
		
		//Assert that the customer saved is not null.
		assertNotNull(savedCustomer);
	}
    
    @Test
	@DisplayName("Testcase to check customer login.")
    @Order(2)
    public void testLoginCustomer() {
    	int cust_id = 1;
    	String password = "Nisha@8080";
    	Customer customer = crepo.loginCustomer(cust_id, password);
    	
    	//Assert that entered password is equal to customer password.
    	assertThat(customer.getPassword()).isEqualTo(password);
    }
    
    @Test
	@DisplayName("Testcase to find all customer.")
    @Order(3)
	public void testFindAllCustomer() {
		List<Customer> list = new ArrayList<>();
		list = crepo.findAll();	
		long rowCount = crepo.count();
		
		//Assert that the size of list of customers is equal to the size of count of rows in database.
		assertThat(list.size()).isEqualTo(rowCount);
	}
    
    @Test
	@DisplayName("Testcase to find Customer By Id.")
    @Order(4)
	public void testFindCustomerById() {
		int id = 1;
		Optional<Customer> cust = crepo.findById(id);
		
		//Assert that customer fetched from database has same id equal to id entered by user.
		assertThat(cust.get().getCustomerId()).isEqualTo(id);		
	}
         
    @Test
    @DisplayName("Test to get customer details by Email.")
    @Order(5)
    public void testFindCustomerByEmail() {
    	String email = "nishachat8080@gmail.com";
    	Customer cust = crepo.findBymailId(email);
    	
    	//Assert that email of customer from database is equal to given emailId.
    	assertThat(cust.getMailId()).isEqualTo(email);  	
    }
    
}