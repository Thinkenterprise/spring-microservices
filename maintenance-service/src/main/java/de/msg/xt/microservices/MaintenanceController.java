package de.msg.xt.microservices;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 
 * @author Michael Schäfer 
 * */

@RestController
public class MaintenanceController {

	protected static Log log = LogFactory.getLog(MaintenanceController.class);
	

	// Discovery Client to discover the eureka server (only eureka profile)
	@Autowired(required=false)
	DiscoveryClient discoveryClient;
		
	// Feign Client (only eureka profile)
	@Autowired(required=false)
	private CarDataClient carDataClient;
		
	// Simple Rest Template and a Ribbon Rest Template if eureka profile is active 
	@Autowired
	private RestTemplate restTemplate;
	

	// URI host:port and seervice name if eureka profile is active 
	@Autowired private String getAll;
	@Autowired private String getCarClient;
	@Autowired private String postCarClient;
	
	// Command which implements an hystrix managed eureka rest call 
	@Autowired(required=false)
	private CarDataHystrixGetCommand carDataHystrixGetCommand;
	
	
	
	/**
	 * Show all instances of the cardata microservice  
	 * 
	 * curl -X GET http://localhost:8085/instance
	 * 
	 * 
	 * @author Michael Schäfer 
	 * 
	 * */
	
	@RequestMapping("instance")
	public String getAllInstance() {
		
		List<ServiceInstance> services = discoveryClient.getInstances("cardata-service");
		
		StringBuffer stringBuffer = new StringBuffer();
	
		for (ServiceInstance serviceInstance : services) {
			stringBuffer.append(serviceInstance.getHost() +":" + serviceInstance.getPort() +":"+ serviceInstance.getUri());
		}
	
		return stringBuffer.toString();
	}
	
	
	
	/**
	 * Get the resource collection of car data 
	 * 
	 * curl -X GET http://localhost:8085/gettAll
	 * 
	 * 
	 * @author Michael Schäfer 
	 * 
	 * */
	
	@RequestMapping("getAll")
	public String getAll() {
		log.debug("getAll called");
		ResponseEntity<List<CarData>> result = restTemplate.exchange(getAll, HttpMethod.GET, null, new ParameterizedTypeReference<List<CarData>>(){});
		return result.getBody().toString();
	}
	

	
	/**
	 * Call collection ressource count times to see how the load balancer work  
	 * with different client implementations 
	 * 
	 * curl -X GET http://localhost:8085/workload/{count}
	 * 
	 * 
	 * @author Michael Schäfer 
	 * 
	 * */
	
	@RequestMapping("workload/{count}/{type}")
	public String workload(@PathVariable int count, @PathVariable int type) {
		log.debug("workload called with parameter " + count);
		ResponseEntity<List<CarData>> result = null;
		List<CarData> rawData = null;
		
		while(count-->0) {
			
			switch(type) {
			
			// Normal Rest Call 
			case 1:
				log.debug("Normal Rest Call ");
				result = restTemplate.exchange(getAll, HttpMethod.GET, null, new ParameterizedTypeReference<List<CarData>>(){});
				rawData=result.getBody();
				break;
			// Feign Rest Call 
			case 2:
				log.debug("Feign Rest Call ");
				result=carDataClient.getAll();
				rawData=result.getBody();
				break;
			// Hystrix rest call 
			case 3:
				log.debug("Hystrix Rest Call ");
				rawData = carDataHystrixGetCommand.carDataGetCommand();
				break;
			
			}
			
			
		}
		return rawData.toString();
	}
	
	
	
	/**
	 * Commit an appointment 
	 * 
	 * @author Michael Schäfer 
	 * 
	 * */
	@RequestMapping(value="commitAppointment",method=RequestMethod.POST)
	public ResponseEntity<Boolean> commitAppointment(Appoinment appointment) {
		
		return new ResponseEntity<Boolean>(new Boolean(true),HttpStatus.OK);
	}
	
}
