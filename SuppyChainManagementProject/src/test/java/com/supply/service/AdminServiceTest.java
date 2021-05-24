package com.supply.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import com.supplychain.beans.Admin;
import com.supplychain.repository.AdminRepository;
import com.supplychain.service.AdminServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AdminServiceTest {

	@Autowired
	private AdminServiceImpl restService;
	
	@MockBean
	private AdminRepository rsRepo;
	
	@Test
	@DisplayName("Testcase to register or save Admin.")
	@Order(1)
	public void testSaveOrUpdateAdmint() {
		
		Admin rest = new Admin();
		rest.setAid(1);
		rest.setAdminName("6STAR");
		rest.setAdminPwd("6star@1234");
		rest.setAdminEmail("star6@gmail.com");
		rest.setAdminAddress("Mumbai");
		
		Mockito.when(rsRepo.saveAndFlush(rest)).thenReturn(rest);
		assertThat(restService.saveOrUpdate(rest)).isEqualTo("Admin Registered successfully");
	}
	
	@Test
	@DisplayName("Testcase to get all registered restaurants.")
	@Order(2)
	public void testGetAllRestaurant() {
		
		Admin rest1 = new Admin();
		rest1.setAid(1);
		rest1.setAdminName("5STAR");
		rest1.setAdminPwd("5star@1234");
		rest1.setAdminEmail("star5@gmail.com");
		rest1.setAdminAddress("Delhi");
		
		Admin rest2 = new Admin();
		rest2.setAid(2);
		rest2.setAdminName("6STAR");
		rest2.setAdminPwd("6star@1234");
		rest2.setAdminEmail("star6@gmail.com");
		rest2.setAdminAddress("Mumbai");
		
		List<Admin> admin = new ArrayList<>();
		admin.add(rest1);
		admin.add(rest2);
		
		Mockito.when(rsRepo.findAll()).thenReturn(admin);
		assertThat(restService.getAllAdmin()).isEqualTo(admin);
	}
	
	@Test
	@DisplayName("Testcase to login admin.")
	@Order(3)
	public void testLoginRestaurant() {
		
		Admin rest = new Admin();
		rest.setAid(1);
		rest.setAdminName("6STAR");
		rest.setAdminPwd("6star@1234");
		rest.setAdminEmail("star6@gmail.com");
		rest.setAdminAddress("Mumbai");
		
		Mockito.when(rsRepo.loginAdmin(1, "6star@1234")).thenReturn(rest);
		assertThat(restService.loginAdmin(1, "6star@1234")).isEqualTo(rest);
	}
	
	@Test
	@DisplayName("Testcase to fetch admin by their id.")
	@Order(4)
	public void testFindAdminById() {
		
		Admin rest = new Admin();
		rest.setAid(1);
		rest.setAdminName("6STAR");
		rest.setAdminPwd("6star@1234");
		rest.setAdminEmail("star6@gmail.com");
		rest.setAdminAddress("Mumbai");
		
		Mockito.when(rsRepo.findByaid(1)).thenReturn(rest);
	    assertThat(restService.findOneById(1)).isEqualTo(rest);
	}
}
