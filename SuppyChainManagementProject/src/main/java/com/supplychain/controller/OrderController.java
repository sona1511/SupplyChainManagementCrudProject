package com.supplychain.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.supplychain.repository.CustomerRepository;
import com.supplychain.repository.OrderRepository;
import com.supplychain.repository.ProductRepository;
import com.supplychain.service.CustomerService;
import com.supplychain.service.OrderService;
import com.supplychain.service.ProductService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/order")
public class OrderController {

	private static final Logger logger=LoggerFactory.getLogger(AdminController.class);

	@Autowired
	OrderService service;

	@Autowired
	OrderRepository oRepo;

	@Autowired
	CustomerRepository cRepo;

	@Autowired
	CustomerService cService;
	
	@Autowired
	ProductRepository mRepo;
	
	@Autowired
	ProductService mService;

	
	/**
	 * the customer can select order
	 * @param customer_id
	 * @param m_id
	 * @param order
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PostMapping("/selectorder/{customer_id}/{m_id}")
	public HashMap<String,Double> selectMenu( @PathVariable("customer_id") int customer_id,@PathVariable("m_id") int m_id,@RequestBody final Order order)throws ResourceNotFoundException {
		Customer c = 
				cRepo.findById(customer_id)
				.orElseThrow(()->new ResourceNotFoundException("Customer not found for this id::"+ customer_id));
		
		Product product = mRepo.findById( m_id)
				.orElseThrow(()->new ResourceNotFoundException("Product not found for this id::"+ m_id));
		

		order.setCustomers(c);
		order.setProduct(product);
		oRepo.saveAndFlush(order);
		HashMap<String,Double> res = service.addOrder(order);
		return res;
	}
	
	
	/**
	 * to get particular order
	 * @param order_id
	 * @return
	 */
	@GetMapping("get/{id}")
	public ResponseEntity allOrder(@PathVariable("id") int order_id){
		logger.info("Order is fetched by order id!!!");
		Optional<Order> o = oRepo.findById(order_id);
		if(o.isEmpty()) {
			return ResponseEntity.badRequest().body("No Order Found");
		}else {
			return ResponseEntity.ok(o);
		}
	}

	
	/**
	 * to order product via customer id
	 * @param customerId
	 * @return
	 */
	@GetMapping("/customer/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") int customerId) {
		logger.info("Order is fetched by customer id!!!");
		Customer c = cRepo.findByCustomerId(customerId);
		List<String> list = oRepo.getOrderList(customerId);
		if (list.isEmpty()) {
			return new ResponseEntity("No order for this customer ID",HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity(list,HttpStatus.OK);
		}
	}

	
	/**
	 * to view all the orders from admin's side
	 * @return
	 */
	@GetMapping()
	public List<Order> orderlist(){
		return oRepo.findAll();
	}


	/**
	 * delete particular order
	 * @param order_id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value="id")int order_id) throws ResourceNotFoundException{
		Order order = oRepo.findById(order_id).orElseThrow(()
				->new ResourceNotFoundException("No order against this order id:"+ order_id));	
		order.setCustomers(null);
		order.setProduct(null);
		oRepo.save(order);
		oRepo.delete(order);
		oRepo.flush();

		Map<String, Boolean> response = new HashMap<>();
		response.putIfAbsent("deleted", Boolean.TRUE);

		return response;
	}

	
	/**
	 * update particular customer
	 * @param orderId
	 * @param orderDetails
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/update/{orderId}")
	public ResponseEntity<Order> updateCustomer(@PathVariable(value="orderId")int orderId,
			@Valid @RequestBody Order orderDetails) throws ResourceNotFoundException{
		
		/**
		 * checking if order against order_id exists or not
		 */
		Order order = oRepo.findById(orderId)
				.orElseThrow(()->new ResourceNotFoundException("Order not found for this id::"+ orderId));
		
		
		/**
		 * order.setMenu(orderDetails.getMenu());
		 */
		order.setQuantity(orderDetails.getQuantity());

		return ResponseEntity.ok(oRepo.saveAndFlush(order));

	}


}