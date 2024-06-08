package com.nsag.pharmacy.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.LinkedTransferQueue;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nsag.pharmacy.entity.Inventory;
import com.nsag.pharmacy.entity.Supplier;
import com.nsag.pharmacy.helper.InventoryModelAssembler;
import com.nsag.pharmacy.helper.SupplierModelAssembler;

import com.nsag.pharmacy.repositry.SupplierRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class SupplierController {
	
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private  SupplierModelAssembler assembler;
	
	@GetMapping("/Suppliers")
	public CollectionModel<EntityModel<Supplier>> ListSupplier(){
		
		List<EntityModel<Supplier>> suppliers = supplierRepository.findAll().stream()
			    .map(assembler::toModel)
			    .collect(Collectors.toList());
		return CollectionModel.of(suppliers,
			      linkTo(methodOn(SupplierController.class).ListSupplier()).withSelfRel());
	}
	
	@GetMapping("/Suppliers/{id}")
	public EntityModel<Supplier> getSupplier(@PathVariable Long id){
		
		Supplier sup = supplierRepository.findById(id).orElseThrow(() ->
        new InventoryNotFoundException(id));
		return assembler.toModel(sup);
	}
	
	@PostMapping("/Suppliers")
	public ResponseEntity<?> newSupplier(@RequestBody Supplier supplier){
		EntityModel<Supplier> entityModel = assembler.toModel(supplierRepository.save(supplier));
		
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
			      .body(entityModel);
	}
	
	@PutMapping("/Suppliers/{id}")
	public ResponseEntity<?>  replaceSupplier(@RequestBody Supplier newsupplier, @PathVariable Long id) {
		Supplier supp =
				supplierRepository.findById(id)
	      .map(supplier -> {
	    	  supplier.setName(newsupplier.getName());
	        
	        return supplierRepository.save(supplier);
	      })
	      .orElseGet(() -> {
	    	  newsupplier.setSupplierId(id);
	        return supplierRepository.save(newsupplier);
	      });
		 EntityModel<Supplier> entityModel = assembler.toModel(supp);

		  return ResponseEntity //
		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		      .body(entityModel);
	  }

	  @DeleteMapping("/Suppliers/{id}")
	  ResponseEntity<?> deleteMedicine(@PathVariable Long id) {

		  supplierRepository.deleteById(id);

		  return ResponseEntity.noContent().build();
		}

}
