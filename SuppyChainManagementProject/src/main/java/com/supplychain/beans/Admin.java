package com.supplychain.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import com.sun.istack.NotNull;

@Entity
@Table(name="Admin",schema ="public")
public class Admin implements Serializable{
	@Id
	@NotNull
	@Column(name="aid")
	private int aid;
	
	@NotBlank
	@Email
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+",message = "enter email in valid format")
	@Column(name="adminEmail")
	private String adminEmail;

	
	@NotNull
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places")
	@Column(name="adminPwd")
	private String adminPwd;
	
	@NotNull
	@Size(min=2,message="Name should atleast have 2 characters")
	@Column(name="adminName")
	private String adminName;
	
	@NotNull
	@Size(min=2,message="Address should atleast have 2 characters")
	@Column(name="adminAddress")
	private String adminAddress;
	


	
	public int getAid() {
		return aid;
	}




	public void setAid(int aid) {
		this.aid = aid;
	}




	public String getAdminEmail() {
		return adminEmail;
	}




	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}




	public String getAdminPwd() {
		return adminPwd;
	}




	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}




	public String getAdminName() {
		return adminName;
	}




	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}




	public String getAdminAddress() {
		return adminAddress;
	}




	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}




	public Admin() {
		super();
	}

	


	public Admin(int aid,
			@NotBlank @Email @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format") String adminEmail,
			@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places") String adminPwd,
			@Size(min = 2, message = "Name should atleast have 2 characters") String adminName,
			@Size(min = 2, message = "Address should atleast have 2 characters") String adminAddress) {
		super();
		this.aid = aid;
		this.adminEmail = adminEmail;
		this.adminPwd = adminPwd;
		this.adminName = adminName;
		this.adminAddress = adminAddress;
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", adminEmail=" + adminEmail + ", adminPwd=" + adminPwd
				+ ", adminName=" + adminName + ", adminAddress=" + adminAddress + "]";
	}


	
	
}
