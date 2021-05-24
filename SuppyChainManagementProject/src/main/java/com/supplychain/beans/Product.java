package com.supplychain.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productid;
	private String productName;

	private double productPrice;

	public Product() {}
	

	public Product(int productid, String productName, double productPrice) {
		super();
		this.productid = productid;
		this.productName = productName;
		this.productPrice =productPrice;
	}


	public int getProductid() {
		return productid;
	}


	public void setProductid(int productid) {
		this.productid = productid;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	@Override
	public String toString() {
		return "Products [productid=" + productid + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", getProductid()=" + getProductid() + ", getProductName()=" + getProductName()
				+ ", getProductPrice()=" + getProductPrice() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}
	