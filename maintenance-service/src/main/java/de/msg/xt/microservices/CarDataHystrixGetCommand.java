package de.msg.xt.microservices;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.EntityReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Profile("eureka")
@Component
public class CarDataHystrixGetCommand {
	
	protected static Log log = LogFactory.getLog(CarDataHystrixGetCommand.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired private String getAll;
	
	/**
	 * 
	 * Call GET Command to receive all ccar data  
	 * 
	 * https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica#configuration
	 * https://github.com/Netflix/Hystrix/wiki/Configuration#CommandExecution
	 * 
	 * @author Michael Schäfer 
	 * 
	 * */
	
	
	@HystrixCommand(fallbackMethod="carDataGetCommandFallback", 
			        commandProperties= {@HystrixProperty(name="hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", value="500")})
	public List<CarData> carDataGetCommand() {
		
		log.debug("carDataGetCommand Get ");
		
		
		// Get call 
		ResponseEntity<List<CarData>> result = restTemplate.exchange(getAll, HttpMethod.GET, null, new ParameterizedTypeReference<List<CarData>>(){});
			
		// Enable to evaluate the header an status information of the http protocoll data 
		if(!result.getStatusCode().is2xxSuccessful())
			throw new RuntimeException("Can't read car data");
	
		// Log successful returned data 
		log.debug("After carDataGetCommand Get " + result.getBody().toString());
		
		return result.getBody();
		
	}
	
	/**
	 * 
	 * Default Value if Hytrix Commands failed 
	 * 
	 * @author Michael Schäfer
	 * */
	
	public List<CarData> carDataGetCommandFallback() {
		
		List<CarData> defaultList = new ArrayList<CarData>();
		defaultList.add(new CarData(-1,-1));
		return defaultList;
		
	}
	
	
	
	
	
	

}
