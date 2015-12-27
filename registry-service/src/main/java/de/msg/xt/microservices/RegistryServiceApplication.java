package de.msg.xt.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * Eureka Server Application 
 * 
 * 
 * @author Michael Schäfer 
 * 
 * */

@SpringBootApplication
@EnableEurekaServer
public class RegistryServiceApplication {

    public static void main(String[] args) {
    	SpringApplication.run(RegistryServiceApplication.class, args);
        //new SpringApplicationBuilder(RegistryServiceApplication.class).web(true).run(args);
    }
}
