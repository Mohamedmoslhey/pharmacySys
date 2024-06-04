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
import com.nsag.pharmacy.helper.InventoryModelAssembler;
import com.nsag.pharmacy.repositry.InventoryRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class InventoryController {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private  InventoryModelAssembler assembler;
	
	@GetMapping("/Medicines")
	public CollectionModel<EntityModel<Inventory>> ListMedicine(){
		
		List<EntityModel<Inventory>> medicines = inventoryRepository.findAll().stream()
			    .map(assembler::toModel)
			    .collect(Collectors.toList());
		return CollectionModel.of(medicines,
			      linkTo(methodOn(InventoryController.class).ListMedicine()).withSelfRel());
	}
	
	@GetMapping("/Medicines/{id}")
	public EntityModel<Inventory> getMedicine(@PathVariable Long id){
		
		Inventory emp = inventoryRepository.findById(id).orElseThrow(() ->
        new InventoryNotFoundException(id));
		return assembler.toModel(emp);
	}
	
	@PostMapping("/Medicines")
	public ResponseEntity<?> newMedicine(@RequestBody Inventory Inventory){
		EntityModel<Inventory> entityModel = assembler.toModel(inventoryRepository.save(Inventory));
		
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
			      .body(entityModel);
	}
	
	@PutMapping("/Medicines/{id}")
	ResponseEntity<?>  replaceMedicine(@RequestBody Inventory newInventory, @PathVariable Long id) {
		Inventory emp =
				inventoryRepository.findById(id)
	      .map(Inventory -> {
	        Inventory.setName(newInventory.getName());
	        
	        return inventoryRepository.save(Inventory);
	      })
	      .orElseGet(() -> {
	        newInventory.setInventoryID(id);
	        return inventoryRepository.save(newInventory);
	      });
		 EntityModel<Inventory> entityModel = assembler.toModel(emp);

		  return ResponseEntity //
		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		      .body(entityModel);
	  }

	  @DeleteMapping("/Inventorys/{id}")
	  ResponseEntity<?> deleteMedicine(@PathVariable Long id) {

		  inventoryRepository.deleteById(id);

		  return ResponseEntity.noContent().build();
		}

}
