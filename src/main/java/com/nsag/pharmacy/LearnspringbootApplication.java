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
import com.nsag.pharmacy.repositry.InventoryRepository;


@SpringBootApplication
public class LearnspringbootApplication {
	 private static final Logger log = LoggerFactory.getLogger(LearnspringbootApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LearnspringbootApplication.class, args);
	}
	
	
    @Bean
	  public CommandLineRunner demo(InventoryRepository repository) {
	    return (args) -> {
	      // save a few Employees
	      repository.save(new Inventory("PANDOL"));
	      repository.save(new Inventory("BILICOL"));
	      repository.save(new Inventory("ROWACOL"));
	      repository.save(new Inventory("C RETARD"));
	      repository.save(new Inventory("Move"));

	      // fetch all Employees
	      log.info("Medicine found with findAll():");
	      log.info("-------------------------------");
	      repository.findAll().forEach(Employee -> {
	        log.info(Employee.toString());
	      });
	      log.info("");

	      // fetch an individual Employee by ID
	      Inventory Inv = repository.findById(1L);
	      log.info("Medicine found with findById(1L):");
	      log.info("--------------------------------");
	      log.info(Inv.toString());
	      log.info("");

	      // fetch Employees by last name
	      log.info("Medicine found with findByLastName('BILICOL'):");
	      log.info("--------------------------------------------");
	      repository.findByName("BILICOL").forEach(jack -> {
	        log.info(jack.toString());
	      });
	      log.info("");
	    };
	  }

}
