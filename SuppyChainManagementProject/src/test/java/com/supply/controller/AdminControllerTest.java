package com.supply.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.supplychain.beans.Admin;
import com.supplychain.controller.AdminController;
import com.supplychain.repository.AdminRepository;
import com.supplychain.service.AdminService;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	Admin admin;
	@MockBean
	AdminRepository rRepo;
	
	@MockBean
	AdminService rService;

	@Test
	@DisplayName("Testcase for get request method.")
	public void getAllAdmin() throws Exception{
		mockMvc.perform(get("http://localhost:8080/admin/getAdmin"))
		.andExpect(status().isOk())
		.andExpect(content().json("[]"));
		
	}
}