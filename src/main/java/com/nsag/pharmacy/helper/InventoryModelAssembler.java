package com.nsag.pharmacy.helper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.nsag.pharmacy.controller.InventoryController;
import com.nsag.pharmacy.entity.Inventory;


@Component
public class InventoryModelAssembler implements RepresentationModelAssembler<Inventory, EntityModel<Inventory>> {
	
  @Override
  public EntityModel<Inventory> toModel(Inventory inventory) {

    return EntityModel.of(inventory, //
        linkTo(methodOn(InventoryController.class).getMedicine(inventory.getInventoryID())).withSelfRel(),
        linkTo(methodOn(InventoryController.class).ListMedicine()).withRel("medcinies"));
  }
}
