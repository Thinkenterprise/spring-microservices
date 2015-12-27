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
	public String getAll() {return "http://localhost:8081/getAll";}

	@Bean 
	public String getCarClient() {return "http://localhost:8081/";}
	
	@Bean 
	public String postCarClient() {return "http://localhost:8081/";}
	
	

	
}
