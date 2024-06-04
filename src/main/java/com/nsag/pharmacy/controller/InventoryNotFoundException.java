package com.nsag.pharmacy.controller;

public class InventoryNotFoundException extends RuntimeException {

	InventoryNotFoundException(Long id) {
	    super("Could not find Medicine " + id);
	  }
}
