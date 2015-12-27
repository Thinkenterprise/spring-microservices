package de.msg.xt.microservices;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


/**
 * Eureka configuration of the api gateway
 * 
 * @author Michael Schäfer 
 * 
 * */
@Profile("eureka")
@EnableDiscoveryClient
@EnableZuulProxy
@Configuration
public class ApiGatewayEurekaConfiguration {

}
