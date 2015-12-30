package de.msg.xt.microservices;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("eureka")
public class UriEurekaConfiguration {

	@Bean 
	public String commitCarData() { return "http://cardata-service/commitAppointment/";}

	@Bean 
	public String commitMaintenance() {return "http://maintenance-service/commitAppointment/";}
	
	

	
	
	
}
