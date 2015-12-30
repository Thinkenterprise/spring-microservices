package de.msg.xt.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Michael Schäfer 
 * */

@Component
public class CarDataIntegrationService {


	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private String commitCarData;
	
	
	/**
	 * Integrate the Car Data Service 
	 * 
	 * @author Michael Schäfer 
	 * */
	
	public boolean commitAppointment(Appoinment appoinment) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		
		HttpEntity<Appoinment> httpEntiy = new HttpEntity<Appoinment>(appoinment,headers);
		
		// Post call 
		ResponseEntity<Boolean> result = restTemplate.exchange(commitCarData, HttpMethod.POST,httpEntiy, Boolean.class);
		return result.getBody();
		
	}
	
	
	
}
