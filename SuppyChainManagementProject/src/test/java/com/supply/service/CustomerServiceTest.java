package com.supply.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import com.supplychain.beans.Customer;
import com.supplychain.repository.CustomerRepository;
import com.supplychain.service.CustomerServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CustomerServiceTest {

	@Autowired
	private CustomerServiceImpl custService;
	
	@MockBean
	private CustomerRepository cRepo;
	
	
	@Test
	@DisplayName("Testcase to register or save customer.")
	@Order(1)
	public void testSaveOrUpdateCustomer() {
		
		Customer customer=new Customer();
		customer.setCustomerId(5);
		customer.setFirstName("smaina");
		customer.setLastName("talwar");
		customer.setPassword("Smaina@4859");
		customer.setMailId("smaina@gmail.com");
		customer.setMobileNo("9087654321");
		customer.setAddress("Mumbai");
		
		Mockito.when(cRepo.saveAndFlush(customer)).thenReturn(customer);
		assertThat(custService.saveOrUpdate(customer)).isEqualTo("customer Registered successfully");
	}
	
	@Test
	@DisplayName("Testcase to get all registered customers.")
	@Order(2)
	public void testGetAllCustomers() {
		
		Customer customer1=new Customer();
		customer1.setCustomerId(1);
		customer1.setFirstName("sony");
		customer1.setLastName("yadav");
		customer1.setMailId("sona@gmail.com");
		customer1.setMobileNo("980475639");
		customer1.setPassword("Sona@1234");
	    
		Customer customer2=new Customer();
		customer2.setCustomerId(2);
		customer2.setFirstName("sony");
		customer2.setLastName("yadav");
		customer2.setMailId("sona@gmail.com");
		customer2.setMobileNo("980475639");
		customer2.setPassword("Sona@1234");
	    
	    List<Customer> customer = new ArrayList<Customer>();
	    customer.add(customer1);
	    customer.add(customer2);
	    
		Mockito.when(cRepo.findAll()).thenReturn(customer);
		assertThat(custService.getAllCustomers()).isEqualTo(customer);
	}
	
	@Test
	@DisplayName("Testcase to login customer.")
	@Order(3)
	public void testLoginCustomer() {
		
		Customer customer=new Customer();
		customer.setCustomerId(2);
		customer.setFirstName("sony");
		customer.setLastName("yadav");
		customer.setMailId("sona@gmail.com");
		customer.setMobileNo("980475639");
		customer.setPassword("Sona@1234");
				
		Mockito.when(cRepo.loginCustomer(2, "Sona@1234")).thenReturn(customer);
		assertThat(custService.loginCustomer(2, "Sona@1234")).isEqualTo(customer);
	}
	
	@Test
	@DisplayName("Testcase to fetch customer by their id.")
	@Order(4)
	public void testFindCustomerById() {
	
		Customer customer=new Customer();
		customer.setCustomerId(2);
		customer.setFirstName("sony");
		customer.setLastName("yadav");
		customer.setMailId("sona@gmail.com");
		customer.setMobileNo("980475639");
		customer.setPassword("Sona@1234");
		
		Mockito.when(cRepo.findByCustomerId(2)).thenReturn(customer);
	    assertThat(custService.findOneById(2)).isEqualTo(customer);
	}
}
