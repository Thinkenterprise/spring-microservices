package de.msg.xt.microservices;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Profile("eureka")
@Component
public class CarDataHystrixPostCommand {
	
	protected static Log log = LogFactory.getLog(CarDataHystrixPostCommand.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired private String postCarClient;
	
	/**
	 * 
	 * Call Post Command to create a CarData  
	 * 
	 * 
	 * @author Michael Schäfer 
	 * 
	 * */
	
	
	@HystrixCommand
	public CarData carDataPostCommand(CarData car) {
		
		log.debug("carDataPostCommand Post " + car.toString());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		
		HttpEntity<CarData> httpEntiy = new HttpEntity<CarData>(car,headers);
		
		// Post call 
		ResponseEntity<CarData> result = restTemplate.exchange(postCarClient, HttpMethod.POST,httpEntiy, CarData.class);
			
		// Enable to evaluate the header an status information of the http protocoll data 
		if(!result.getStatusCode().is2xxSuccessful())
			throw new RuntimeException("Cant create car data");
	
		// Log successful returned data 
		log.debug("After carDataPostCommand Post " + result.getBody().toString());
		
		return result.getBody();
		
	}
	
	

}
