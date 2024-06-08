package com.nsag.pharmacy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nsag.pharmacy.entity.Inventory;
import com.nsag.pharmacy.entity.Supplier;
import com.nsag.pharmacy.repositry.InventoryRepository;
import com.nsag.pharmacy.repositry.SupplierRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

    @Autowired 
    private SupplierRepository supplierRepository;
    
	
    public Inventory saveItemInInventory(Inventory inventory) {
        // Check if supplier exists
    	Supplier existingSupplier = null ;
    	       if(!supplierRepository.findByName(inventory.getSupplier().getName()).isEmpty()){
    		 existingSupplier = supplierRepository.findByName(inventory.getSupplier().getName()).get(0);
    	       }
    	
        

        if (existingSupplier == null) {
            // Create a new supplier if it doesn't exist
            existingSupplier = supplierRepository.save(inventory.getSupplier());
        }

        // Set the saved supplier to the medicine
        inventory.setSupplier(existingSupplier);

        // Save the medicine with the assigned supplier
        
   
    	return inventoryRepository.save(inventory);
    }
    
    public List<Inventory> findAll() {
          return inventoryRepository.findAll();
    }

	public Inventory findById(long l) {
		// TODO Auto-generated method stub
		 return inventoryRepository.findById(l);
	}

	public List<Inventory> findByName(String string) {
		// TODO Auto-generated method stub
		return inventoryRepository.findByName(string);
	}
}

