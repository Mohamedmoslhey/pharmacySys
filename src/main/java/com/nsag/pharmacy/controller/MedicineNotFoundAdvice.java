package com.nsag.pharmacy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MedicineNotFoundAdvice{

	@ExceptionHandler(InventoryNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String medicineNotFoundHandler(InventoryNotFoundException ex) {
	    return ex.getMessage();
	  }
}
