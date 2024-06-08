package com.nsag.pharmacy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long supplierId;
	private String name;
public Supplier() {}
	
public Supplier(String name) {
		
		this.name = name;
		
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
