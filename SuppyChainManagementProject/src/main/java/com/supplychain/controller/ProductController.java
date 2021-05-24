package com.supplychain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.beans.Customer;
import com.supplychain.beans.Order;
import com.supplychain.beans.Product;
import com.supplychain.exception.ResourceNotFoundException;
import com.supplychain.repository.ProductRepository;
import com.supplychain.service.ProductService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductRepository mRepo;
	
	@Autowired
	ProductService service;
	
	
	@PostMapping
	public Product create(@RequestBody final Product product) {
		return mRepo.saveAndFlush(product);
	}

	
	@GetMapping
	public List<Product> list(){
		return mRepo.findAll();
	}
	
	/**
	 * to get particular menu
	 * @param productid
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Product> allOrder(@PathVariable("id") int productid){
		return mRepo.findById((int) productid);
	}
	
	@DeleteMapping("/delete/{productid}")
	public Map<String, Boolean> delete(@PathVariable(value="productid")int productid) throws ResourceNotFoundException{
		Product product = mRepo.findById(productid)
				.orElseThrow(()->new ResourceNotFoundException("Menu not found for this id::"+ productid));
		
		this.mRepo.delete(product);
		
		Map<String, Boolean> response = new HashMap<>();
		response.putIfAbsent("deleted", Boolean.TRUE);
		
		return response;
	
	}
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value="id")int productid,
			@Valid @RequestBody Product product) throws ResourceNotFoundException{
		Product m = this.mRepo.findById(productid)
				.orElseThrow(()->new ResourceNotFoundException("Product item found for this id::"+ productid));
		
		m.setProductName(product.getProductName());
		m.setProductPrice(product.getProductPrice());
		
		return ResponseEntity.ok(this.mRepo.save(m));
	}

	
}
