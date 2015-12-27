package de.msg.xt.microservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Application of the API Gateway Service 
 * 
 * Possible sample calls  
 * 
 * curl -X GET http://localhost:8090/cardata/getAll
 * curl -X GET http://localhost:8090/maintenance/instance
 * 
 * 
 * @author Michael Schäfer 
 * 
 * */

@SpringBootApplication
@RestController
public class ApiGatewayApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}


}
