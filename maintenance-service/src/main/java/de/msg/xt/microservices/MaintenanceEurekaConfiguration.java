package de.msg.xt.microservices;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("eureka")
@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@Configuration

/**
 * Enable all necessary eureka feature (discovery, hystrix and feign clients) 
 * 
 * @author Michael Schäfer
 * 
 * */


public class MaintenanceEurekaConfiguration {

}
