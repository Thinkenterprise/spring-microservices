package de.msg.xt.microservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarDataController {

	protected static Log log = LogFactory.getLog(CarDataController.class);
	
	@Value("${helloWorld}")
	private String helloWorld;
	
	private static List<CarData> carDatas = new ArrayList<CarData>();
	
	private static int count;
	
	
	/**
	 * Simple Hello World Method 
	 * 
	 * 
	 * @author Michael Schäfer 
	 * 
	 * */
	@RequestMapping(value="helloWorld", method=RequestMethod.GET)
	public ResponseEntity<String> helloWorld() {
		log.debug(helloWorld);
		return new ResponseEntity<String>(helloWorld,HttpStatus.OK);
	}

	
	/**
	 * 
	 * Simple initializing of the controller data.  
	 * 
	 * @author Michael Schäfer 
	 * */
	@PostConstruct
	public void initialize() {
		
		for(int i = 0; i < 10; ++i) {
			CarData carData = new CarData(i,i*10);
			carDatas.add(carData);
		}
	}
	
	/**
	 * 
	 * Simple collection  with two additional feature. 
	 * First an simple delay to simulate load balancing. 
	 * Second the method throw an exception if the method call three times 
	 * to simulate an hystrix command.  
	 * 
	 * 
	 * curl -X GET http://localhost:8081/
	 * 
	 * @author Michael Schäfer 
	 * */
	@RequestMapping(value="getAll", method=RequestMethod.GET)
	public ResponseEntity<List<CarData>> getAll() {
		
		log.debug("getAll called");
		
		// simple delay 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		
		// Throws an exception 
		if(count++>3) {
			count=0;
			throw new RuntimeException("hystrix simulated exception");
		}
		
		return new ResponseEntity<List<CarData>>(carDatas, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * curl -H "Content-Type: application/json" -X POST -d '{"id":0,"value":1000}' http://localhost:8081/
	 * 
	 * @author Michael Schäfer 
	 * */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CarData> create(@RequestBody CarData car) {
		
		log.debug("create called " + car.toString());
		
		carDatas.add(car);
		
		Random random = new Random();
		Integer randomInteger = new Integer(random.nextInt());
		car.setValue(randomInteger);
		
		return new ResponseEntity<CarData>(car, HttpStatus.OK);
	}
	
	
	
	
}
