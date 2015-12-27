package de.msg.xt.microservices;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * Declarative Rest Client for Feign 
 * 
 * @author Michale Schaefe 
 * 
 * */

@FeignClient("cardata-service")
public interface CarDataClient {
	
	@RequestMapping(value="getAll", method=RequestMethod.GET)
	public ResponseEntity<List<CarData>> getAll();
	
}
