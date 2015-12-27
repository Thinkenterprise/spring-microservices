package de.msg.xt.microservices;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("eureka")
@EnableEurekaClient
@Configuration
public class CarDataEurekaConfiguration {

}
