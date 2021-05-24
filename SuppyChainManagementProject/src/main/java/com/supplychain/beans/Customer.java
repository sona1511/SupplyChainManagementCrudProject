package com.supplychain.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="customer",schema ="public")
public class Customer implements Serializable{
		
		@Id
		@NotNull
		@Column(name="customerId")
		private int customerId;
		
		@NotNull
		@Size(min=2,message="First_Name should atleast have 2 characters")
		@Column(name="firstName")
		private String firstName;
		
		@NotNull
		@Size(min=2,message="Last_Name should atleast have 2 characters")
	    @Column(name="lastName")
	    private String lastName;
		
		@NotNull
		@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places")
	    @Column(name="password")
	    private String password;
	    
	    @NotBlank
		@Email
		@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+",message = "enter email in valid format")
	    @Column(name="mailId")
	    private String mailId;
	    
	    @NotNull
		@Size(min=10,message="Enter 10 digit Phone_no")
	    @Column(name="mobileNo")
	    private String mobileNo;
	    
	    @NotNull
		@Size(min=2,message="Address should atleast have 2 characters")
	    @Column(name="address")
		private String address;
	    
	    @OneToMany(mappedBy = "customers",cascade = CascadeType.ALL)
	    @JsonIgnoreProperties({"customers","hibernateLazyInitializer", "handler"})
		@LazyCollection(LazyCollectionOption.FALSE)
		private List<Order> orders;

		public Customer() {
			super();
		}


		public Customer(@NotNull int customerId,
				@NotNull @Size(min = 2, message = "First_Name should atleast have 2 characters") String firstName,
				@NotNull @Size(min = 2, message = "Last_Name should atleast have 2 characters") String lastName,
				@NotNull @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places") String password,
				@NotBlank @Email @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format") String mailId,
				@NotNull @Size(min = 10, message = "Enter 10 digit Phone_no") String mobileNo,
				@NotNull @Size(min = 2, message = "Address should atleast have 2 characters") String address) {
			super();
			this.customerId = customerId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
			this.mailId = mailId;
			this.mobileNo = mobileNo;
			this.address = address;
		}






		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getMailId() {
			return mailId;
		}

		public void setMailId(String mailId) {
			this.mailId = mailId;
		}

		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
		

		public List<Order> getOrders() {
			return orders;
		}



		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}



		@Override
		public String toString() {
			return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", password=" + password + ", mailId=" + mailId + ", mobileNo=" + mobileNo + ", address="
					+ address + "]";
		}

	    
	    
	    
}
