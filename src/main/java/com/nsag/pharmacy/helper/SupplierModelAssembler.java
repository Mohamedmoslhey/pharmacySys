package com.nsag.pharmacy.helper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.nsag.pharmacy.controller.SupplierController;
import com.nsag.pharmacy.entity.Supplier;


@Component
public class SupplierModelAssembler implements RepresentationModelAssembler<Supplier, EntityModel<Supplier>> {
	
  @Override
  public EntityModel<Supplier> toModel(Supplier supplier) {

    return EntityModel.of(supplier, //
        linkTo(methodOn(SupplierController.class).getSupplier(supplier.getSupplierId())).withSelfRel(),
        linkTo(methodOn(SupplierController.class).ListSupplier()).withRel("supplieres"));
  }
}
