package com.nsag.pharmacy.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsag.pharmacy.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findByName(String lastName);

	Inventory findById(long id);
}
