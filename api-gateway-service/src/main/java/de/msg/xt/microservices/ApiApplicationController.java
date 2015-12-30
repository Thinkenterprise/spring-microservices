package de.msg.xt.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 
 * 
 * @author Michael Schäfer 
 * 
 * */
@RestController
public class ApiApplicationController {

	@Autowired
	private CarDataIntegrationService carDataIntegrationService;
	
	@Autowired
	private MaintenanceIntegrationService maintenanceIntegrationService;
	
	/**
	 * A client commit an appointment  
	 * 
	 * curl -X POST http://localhost:8090/commitAppointment/ --header "Content-Type:application/json" -d '{"name":"Tire"}'
	 * 
	 * 
	 * @author Michael Schäfer 
	 * */
	@RequestMapping(value="commitAppointment", method=RequestMethod.POST)
	public void commitAppointment(@RequestBody Appoinment appoinment) {
	
		// commit the appointment over the car data service 
		carDataIntegrationService.commitAppointment(appoinment);
		
		// commit the appointment over the maintenance service 
		maintenanceIntegrationService.commitAppointment(appoinment);
	
	} 
	
	/**
	 * Get an Appointment Resource  
	 * 
	 * @author Michael Schäfer 
	 * 
	 * */
	
	
	@RequestMapping
	public ResponseEntity<Appoinment> getAll() {
		
		return new ResponseEntity<Appoinment>(new Appoinment("Tire"),HttpStatus.OK);
		
	}
	
	
	
	
}
