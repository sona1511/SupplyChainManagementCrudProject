package com.supplychain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.beans.Admin;
import com.supplychain.beans.Customer;
import com.supplychain.exception.CustomerException;
import com.supplychain.exception.ResourceNotFoundException;
import com.supplychain.repository.CustomerRepository;
import com.supplychain.service.CustomerService;
/**
 * 
 * @author sonyyada
 *
 */
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	private static final Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerRepository cRepo;
	
	@Autowired
	CustomerService customerService;
	/**
	 * @param Customer
	 * @return
	 * @throws CustomerException 
	 * @throws Exception
	 */
	
	/**
	 * Customer registration
	 * @param customer
	 * @return
	 * @throws CustomerException
	 */
	

		
	@PostMapping("/Register")
	private String saveCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		if (customer.getCustomerId() > 0) {
			if (customer.getFirstName() != null && customer.getLastName() != null && customer.getAddress() != null
					&& customer.getMailId() != null && customer.getPassword() != null || customer.getMobileNo() != null)
				return customer.getCustomerId() + " " + customerService.saveOrUpdate(customer);
			else {
				logger.error("Exception Occured!!! Customer field has incorrect data");
				throw new CustomerException(
						" Exception Occured!!! INVALID Details!!!Please Check Details");
			}
		} else {
			logger.error("Exception Occured!!! Customer field has incoreect data");
			throw new CustomerException(" Exception Occured!!! INVALID ID!!!Please Check customerId");
		}
		
	}
	
	
	/**
	 *  Customer login
	 * @param customerId
	 * @param password
	 * @return
	 */
	/**
	 * http://localhost:9095/customer/login?customerId=2&password=Juhi@8080
	 * @param customerId
	 * @param password
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<Customer> loginUser(@Valid @RequestParam("customerId") int customerId, @Valid @RequestParam("password") String password) {
		logger.info("Customer Login Method Started!");
		Customer customer = customerService.loginCustomer(customerId, password) ;
		if (customer != null) {
			return new ResponseEntity("Login Successfull!!!", HttpStatus.OK);
		}
		return new ResponseEntity("Login Failed!!!", HttpStatus.BAD_REQUEST);

}
	
	
	/**
	 * To get all customers
	 * @return
	 */
	
	@GetMapping("/getCustomers")
	private List<Customer> getAllCustomers() {
		logger.info("Customers Retrived");
		return customerService.getAllCustomers();
	}

	/**
	 * To get customers by Id
	 * @param customerId
	 * @param customer
	 * @return
	 */
	
		@GetMapping("/{customerId}")
		public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") int customerId, Customer customer) {
			logger.info("Customer is fetched by customer id!!!");
			if (customer.getCustomerId() == customerId) {
				return ResponseEntity.ok(customerService.findOneById(customerId));
			} else {
				return ResponseEntity.badRequest().build();
				}
			}
	
	/**
	 * customer logout
	 * @return
	 */
	 @GetMapping("/logout")
	    public String exit() {
	    	return "You have been logged out";
	    }

	 /**
	  * delete particular customer
	  * @param customerId
	  * @return
	  * @throws ResourceNotFoundException
	  */
	 @DeleteMapping("/delete/{customerId}")
		public Map<String, Boolean> delete(@PathVariable(value="customerId")int customerId) throws ResourceNotFoundException{
			Customer customer = cRepo.findById(customerId)
					.orElseThrow(()->new ResourceNotFoundException("Customer not found for this id::"+ customerId));
			
			this.cRepo.delete(customer);
			
			Map<String, Boolean> response = new HashMap<>();
			response.putIfAbsent("deleted", Boolean.TRUE);
			
			return response;
		}
	 
	 /**
	  * update particular customer
	  * @param customerId
	  * @param customerDetails
	  * @return
	  * @throws ResourceNotFoundException
	  */
	 @PutMapping("/updateCustomer/{customerId}")
		public ResponseEntity<Customer> updateCustomer(@PathVariable(value="customerId")int customerId,
				@Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException{
			Customer customer = this.cRepo.findById(customerId)
					.orElseThrow(()->new ResourceNotFoundException("Customer not found for this id::"+ customerId));
			
			customer.setMailId(customerDetails.getMailId());
			customer.setFirstName(customerDetails.getFirstName());
			customer.setLastName(customerDetails.getLastName());
			customer.setAddress(customerDetails.getAddress());
			customer.setMobileNo(customerDetails.getMobileNo());
			customer.setPassword(customerDetails.getPassword());
			
			return ResponseEntity.ok(this.cRepo.saveAndFlush(customer));
		}
	
}







