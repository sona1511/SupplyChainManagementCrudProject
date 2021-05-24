package com.supply.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.supplychain.beans.Customer;
import com.supplychain.controller.CustomerController;
import com.supplychain.repository.CustomerRepository;
import com.supplychain.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	Customer customer;
	@MockBean
	CustomerRepository cRepo;
	
	@MockBean
	CustomerService cService;

	@Test
	@DisplayName("Testcase for get request method.")
	public void getAllCustomer() throws Exception{
		mockMvc.perform(get("/getCustomers"))
		.andExpect(status().isOk())
		.andExpect(content().json("[]"));
		
	}
}
