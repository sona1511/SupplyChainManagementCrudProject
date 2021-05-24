package com.supply.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.supplychain.beans.Product;
import com.supplychain.repository.ProductRepository;
import com.supplychain.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductServiceImpl menuService;
	
	@MockBean
	private ProductRepository mRepo;
	
	
	@Test
	@DisplayName("Testcase to get list of Product.")
	public void testGetProduct() {
		
		List<String> list =  new ArrayList<>();
		List<Product> product = new ArrayList<>();
		product = mRepo.findAll();
		for(Product m:product)
		{
			String name = m.toString();
			list.add(name);
		}
		
		Mockito.when(mRepo.getProduct()).thenReturn(list);
		assertThat(menuService.getProduct()).isEqualTo(list);
	}

}
