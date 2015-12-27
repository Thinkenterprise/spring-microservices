package de.msg.xt.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	 * A client commit an apponment 
	 * 
	 * @author Michael Schäfer 
	 * */
	@RequestMapping
	public void commitAppointment(@RequestBody Appoinment appoinment) {
	
		// commit the appointment over the car data service 
		carDataIntegrationService.commitAppointment(appoinment);
		
		// commit the appointment over the maintenance service 
		maintenanceIntegrationService.commitAppointment(appoinment);
	
	} 
	
	
	
}
