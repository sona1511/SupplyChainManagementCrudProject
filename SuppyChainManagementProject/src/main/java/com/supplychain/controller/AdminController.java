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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.beans.Admin;
import com.supplychain.beans.Customer;
import com.supplychain.beans.Product;
import com.supplychain.exception.CustomerException;
import com.supplychain.exception.ResourceNotFoundException;
import com.supplychain.repository.AdminRepository;
import com.supplychain.repository.OrderRepository;
import com.supplychain.repository.ProductRepository;
import com.supplychain.service.AdminService;
@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/Admin")
public class AdminController {
	private static final Logger logger=LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminRepository rRepo;
	
	@Autowired
	AdminService rService;
	
	
	
	/**
	 * admin registration
	 * @param admin
	 * @return
	 * @throws CustomerException
	 */
	
	@PostMapping("/Register")
	private String saveAdmin(@Valid @RequestBody Admin admin) throws CustomerException {
		if (admin.getAid()> 0) {
			if (admin.getAdminName()!= null && admin.getAdminAddress() != null
					&& admin.getAdminEmail() != null && admin.getAdminPwd() != null)
				return admin.getAid() + " " + rService.saveOrUpdate(admin);
			else {
				logger.error("Exception Occured!!! Admin field has incorrect data");
				throw new CustomerException(
						" Exception Occured!!! INVALID Details!!!Please Check Details");
			}
		} else {
			logger.error("Exception Occured!!! Admin field has incoreect data");
			throw new CustomerException(" Exception Occured!!! INVALID ID!!!Please Check aid");
		}
		
	}
	
	
	/**
	 * Admin login
	 * @param aid
	 * @param adminPwd
	 * @return
	 */
	/**
	 * Url Example:  http://localhost:9095/restaurant/login?rid=2&password=Juhi@8080
	 * @param aid
	 * @param adminPwd
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<Admin> loginUser(@Valid @RequestParam("aid") int aid, @Valid @RequestParam("adminPwd") String adminPwd) {
			logger.info("Admin Login Method Started!");
			Admin admin = rService.loginAdmin(aid, adminPwd);
			if (admin != null) {
				return new ResponseEntity("Login Successfull!!!", HttpStatus.OK);
			}
			return new ResponseEntity("Login Failed!!!", HttpStatus.BAD_REQUEST);

	}
	
	
	/**
	 * To get all admin
	 * @return
	 */
	
	@GetMapping()
	private List<Admin> getAllAdmin() {
		logger.info("Admin Retrived");
		return rService.getAllAdmin();
	}


	/**
	 * To get admin by Id
	 * @param aid
	 * @param admin
	 * @return
	 */
	
	@GetMapping("/profile/{aid}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("aid") int aid, Admin admin) {
		logger.info("Admin is fetched by admin id!!!");
		if (admin.getAid() == aid) {
			return ResponseEntity.ok(rService.findOneById(aid));
		} else {
			return ResponseEntity.badRequest().build();
			}
		}
	
	
	/**
	 * admin logout
	 * @return
	 */
	 @GetMapping("/logout")
	    public String exit() {
	    	return "You have been logged out";
	    }
	
	
	/**
	 * delete particular admin
	 * @param aid
	 * @return
	 * @throws ResourceNotFoundException
	 */
		 @DeleteMapping("/delete/{aid}")
			public Map<String, Boolean> delete(@PathVariable(value="aid")int aid) throws ResourceNotFoundException{
				Admin admin = rRepo.findById(aid)
						.orElseThrow(()->new ResourceNotFoundException("Admin not found for this id::"+ aid));
				
				this.rRepo.delete(admin);
				
				Map<String, Boolean> response = new HashMap<>();
				response.putIfAbsent("deleted", Boolean.TRUE);
				
				return response;
			}
		 
		 @PutMapping("/update/{aid}")
			public ResponseEntity<Admin> updateAdmin(@PathVariable(value="aid")int aid,
					@Valid @RequestBody Admin res) throws ResourceNotFoundException{
				Admin admin = this.rRepo.findById(aid)
						.orElseThrow(()->new ResourceNotFoundException("Admin not found for this id::"+ aid));
				
			
				admin.setAdminEmail(res.getAdminEmail());
				admin.setAdminName(res.getAdminName());
				admin.setAdminPwd(res.getAdminPwd());
				admin.setAdminAddress(res.getAdminAddress());
				
				return ResponseEntity.ok(this.rRepo.saveAndFlush(admin));
			}
	
}






