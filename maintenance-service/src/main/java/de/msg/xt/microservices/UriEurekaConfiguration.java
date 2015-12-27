package de.msg.xt.microservices;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("eureka")
public class UriEurekaConfiguration {

	@Bean 
	public String getAll() {return "http://cardata-service/getAll";}

	@Bean 
	public String getCarClient() {return "http://cardata-service/";}
	
	@Bean 
	public String postCarClient() {return "http://cardata-service/";}
	
	
	
	
	
}
