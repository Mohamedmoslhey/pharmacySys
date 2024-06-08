package com.nsag.pharmacy.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long InventoryID;
	
	@Column(nullable = false)
	private String name;
	private String Description;
	private String DosageForm;
	private String Strength;
	
	
	private BigDecimal UnitPrice;
	
	
	private int CurrentStock;
	
	
	private int ReorderPoint;
	
	private Date ExpiryDate ;
	
	
	  @JoinColumn(name = "supplier_id")
	  @ManyToOne
	  private Supplier supplier;
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Inventory() {
		
	}
	public Inventory(String name) {
		
		this.name = name;
		
	}
	
public Inventory(String name,Supplier supplier) {
		
		this.name = name;
		this.supplier = supplier;
		
	}
	public Long getInventoryID() {
		return InventoryID;
	}
	public void setInventoryID(Long inventoryID) {
		InventoryID = inventoryID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getDosageForm() {
		return DosageForm;
	}
	public void setDosageForm(String dosageForm) {
		DosageForm = dosageForm;
	}
	public String getStrength() {
		return Strength;
	}
	public void setStrength(String strength) {
		Strength = strength;
	}
	public BigDecimal getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(BigDecimal  unitPrice) {
		UnitPrice = unitPrice;
	}
	public int getCurrentStock() {
		return CurrentStock;
	}
	public void setCurrentStock(int currentStock) {
		CurrentStock = currentStock;
	}
	public int getReorderPoint() {
		return ReorderPoint;
	}
	public void setReorderPoint(int reorderPoint) {
		ReorderPoint = reorderPoint;
	}

	public Date getExpiryDate() {
		return ExpiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		ExpiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "Inventory [InventoryID=" + InventoryID + ", name=" + name + ", Description=" + Description
				+ ", DosageForm=" + DosageForm + ", Strength=" + Strength + ", UnitPrice=" + UnitPrice
				+ ", CurrentStock=" + CurrentStock + ", ReorderPoint=" + ReorderPoint + ", ExpiryDate=" + ExpiryDate
				+ ", supplier=" + supplier + "]";
	}
	
}
