package com.supplychain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.supplychain.beans.Admin;
import com.supplychain.beans.Customer;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
	
	Admin findByaid(int aid);
	
	@Query("select r from Admin r where r.aid=:aid AND r.adminPwd=:adminPwd")
	public Admin loginAdmin(@Param("aid") int aid,@Param("adminPwd") String adminPwd);
	
	public Admin findByadminEmail(String email); 

}
