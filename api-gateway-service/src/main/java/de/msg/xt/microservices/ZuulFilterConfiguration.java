package de.msg.xt.microservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ZuulFilterConfiguration {

	@Profile("eureka")
	@Bean
	public ZuulLoggingFilter getZuulLoggingFilter() {
		
		return new ZuulLoggingFilter();
		
	}
	
	
	
}
