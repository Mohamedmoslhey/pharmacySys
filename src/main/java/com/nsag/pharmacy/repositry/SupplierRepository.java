package com.nsag.pharmacy.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsag.pharmacy.entity.Inventory;
import com.nsag.pharmacy.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	List<Supplier> findByName(String lastName);
	

	Supplier findById(long id);
}
