package de.msg.xt.microservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration of the simple rest template 
 * 
 * 
 * @author Michael Schäfer
 * 
 * */


@Configuration
public class RestTemplateConfiguration {

	@Profile("native")
	@Primary                      
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	
	
}
