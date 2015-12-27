package de.msg.xt.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implementation  of the Maintenance microservices application 
 * 
 * 
 * @ToDo Asynchrone and reactive hystrixs commands 
 * @ToDo Default Value Problem 
 * 
 * 
 * @author Michael Schäfer 
 * 
 * */

@SpringBootApplication
public class MaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceApplication.class, args);
	}
	
}
