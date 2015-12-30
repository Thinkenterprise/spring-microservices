package de.msg.xt.microservices;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
/**
 * Simple URI Configuration without eureka support and profile 
 * 
 * @author Michael Schäfer 
 * */

@Profile("native")
@Configuration
public class UriConfiguration {

	@Bean 
	public String commitCarData() { return "http://localhost:8081/commitAppointment/";}

	@Bean 
	public String commitMaintenance() {return "http://localhost:8085/commitAppointment/";}
	
	
	

	
}
