package com.supplychain.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplychain.beans.Admin;
import com.supplychain.beans.Customer;
import com.supplychain.repository.AdminRepository;
import com.supplychain.repository.OrderRepository;
@Transactional
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminRepository rRepo;

	@Override
	public String saveOrUpdate(Admin admin) {
		rRepo.saveAndFlush(admin);
		return "Admin Registered successfully";
	}

	@Override
	public List<Admin> getAllAdmin() {
       	return rRepo.findAll();
	}

	@Override
	public Admin loginAdmin(int aid, String adminPwd) {
		return rRepo.loginAdmin(aid,adminPwd);
	}

	@Override
	public Admin findOneById(int aid) {
		return rRepo.findByaid(aid);
	}


	
}
