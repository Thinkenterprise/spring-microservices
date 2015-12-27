package de.msg.xt.microservices;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	//@Autowired
	//private CarDataHystrixPostCommand carDataHystrixPostCommand;
	
	
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
	
	
	
	/*
	
	
	public String exceptionFallback() {
		return "exceptionFallback";
		
	}
	
	@RequestMapping("instance")
	public String getAllInstance() {
		
		List<ServiceInstance> services = discoveryClient.getInstances("cardata-service");
		
		StringBuffer stringBuffer = new StringBuffer();
	
		for (ServiceInstance serviceInstance : services) {
			stringBuffer.append(serviceInstance.getHost() + serviceInstance.getPort() + serviceInstance.getUri());
		}
	
		return stringBuffer.toString();
	}
	
	
	
	
	@RequestMapping("workload")
	@HystrixCommand(fallbackMethod="exceptionFallback")
	public String workload() {
		System.out.println("MaintenanceController:workload");
		ResponseEntity<String> result= null;
		
		while(true) {		
			 result = restTemplate.exchange(helloWorld, HttpMethod.GET, null, String.class);
			 System.out.println(result.getBody());
		}
			
			
	}
	
	
	
	@RequestMapping("helloWorld")
	public String helloWorld() {
		System.out.println("MaintenanceController:helloWorld");
		ResponseEntity<String> result = restTemplate.exchange(helloWorld, HttpMethod.GET, null, String.class);
		return result.getBody();
	}
	
	
	@RequestMapping("helloWorldFeign")
	public String helloWorldFeign() {
		System.out.println("MaintenanceController:helloWorldFeign");
		ResponseEntity<String> result = carDataDataControllerClient.helloWorld(); 
		return result.getBody();
	}

	
	
	@RequestMapping("callGetCarClient")
	public void callGetCarClient() {
		System.out.println("MaintenanceController:callGetCarClient");
		ResponseEntity<CarData> result = restTemplate.exchange(getCarClient, HttpMethod.GET, null, CarData.class);
		System.out.println(result.getBody().toString());
		
		
	}
	
	
	
	

	@RequestMapping("callPostCarClient")
	public void callPostCarClient() {
		System.out.println("MaintenanceController:callPostCarClient");
		CarData car = new CarData("VW", "Passat CC");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		
		HttpEntity<CarData> httpEntiy = new HttpEntity<CarData>(car,headers);
		
		ResponseEntity<CarData> result = restTemplate.exchange(postCarClient, HttpMethod.POST,httpEntiy, CarData.class);
			
		System.out.println(result.getBody().toString());
		
	}
	
	
	@RequestMapping("callPostCarClientFor")
	public void callPostCarClientFor() {
		System.out.println("MaintenanceController:callPostCarClient");
		CarData car = new CarData("VW", "Passat CC");
		
		CarData result = restTemplate.postForObject(postCarClient, car, CarData.class);
		
		System.out.println(result.toString());
		
	}
	
	@RequestMapping("callPostCarClientHystrixFor")
	public void callPostCarClientHystrixFor() {
		System.out.println("MaintenanceController:callPostCarClientHystrixFor");
		
		System.out.println(callPostCarClientFor.callPostCarClientFor().toString());
		
	}
	
	@RequestMapping("callPostCarClientHystrixForBoolean")
	public void callPostCarClientHystrixForBoolean() {
		System.out.println("MaintenanceController:callPostCarClientHystrixForBoolean");
		
		System.out.println(callPostCarClientForBoolean.callPostCarClientFor().toString());
		
	}
	
	*/
	
	
}
