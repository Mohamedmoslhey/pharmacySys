package com.nsag.pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nsag.pharmacy.configuration.DatabaseInitializer;
import com.nsag.pharmacy.entity.Inventory;
import com.nsag.pharmacy.entity.Supplier;
import com.nsag.pharmacy.repositry.InventoryRepository;
import com.nsag.pharmacy.service.InventoryService;


@SpringBootApplication
public class LearnspringbootApplication {
	 private static final Logger log = LoggerFactory.getLogger(LearnspringbootApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LearnspringbootApplication.class, args);
	}
	
	
    @Bean
	  public CommandLineRunner demo(InventoryService serv) {
	    return (args) -> {
	      // save a few Employees
	    	Supplier sup1 = new Supplier("Abipco");
	    	serv.saveItemInInventory(new Inventory("PANDOL",sup1));
	    	Supplier sup2 = new Supplier("Amoun");
	    	serv.saveItemInInventory(new Inventory("BILICOL",sup2));
	    	Supplier sup3 = new Supplier("Alex-Pharama");
	    	serv.saveItemInInventory(new Inventory("ROWACOL",sup3));
	    	Supplier sup4 = new Supplier("Adico");
	    	serv.saveItemInInventory(new Inventory("C RETARD",sup4));
	    	Supplier sup5 = new Supplier("Eva");
	    	serv.saveItemInInventory(new Inventory("Move",sup5));

	      // fetch all Employees
	      log.info("Medicine found with findAll():");
	      log.info("-------------------------------");
	      serv.findAll().forEach(Employee -> {
	        log.info(Employee.toString());
	      });
	      log.info("");

	      // fetch an individual Employee by ID
	      Inventory Inv = serv.findById(1L);
	      log.info("Medicine found with findById(1L):");
	      log.info("--------------------------------");
	     if(Inv ==null){
	    	 log.info("Medicine found with findById(1L): Not Exist");	 
	     }else{
	      log.info(Inv.toString());
	     }
	      log.info("");

	      // fetch Employees by last name
	      log.info("Medicine found with findByLastName('BILICOL'):");
	      log.info("--------------------------------------------");
	      serv.findByName("BILICOL").forEach(jack -> {
	        log.info(jack.toString());
	      });
	      log.info("");
	    };
	  }

}
