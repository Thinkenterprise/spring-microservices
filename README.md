# spring-microservices


The Sample Project is used in the Microservices Pattern in Spring implementieren - ShortCut. 

##Quickstart

###Prerequisites
- Java SE 7, 8
- git
- maven
- bash

###Clone repository
`````
git clone https://github.com/Thinkenterprise/spring-microservices.git
cd spring-microservices
```

###Build Microservices
The microservice projects are strictly seperated. Thats the nature of microservices.
The microservices share nothing. Therfore we haven't a parent POM and to build the 
microservices artefacts separately. The build script support you.  
```
./buildservices.sh 
```

###Start Microservice Logging
The logging script calls tail and print the log files which each microservice generates. 
```
./logservices.sh 
```


##Start Microservices:
The running script start all microserves in the right order with an delay between them
on the right port with the eureka profile. 
```
./runservice.sh
```

##Simple Tests:

Service Registry Test:

Call the Eureka Service Registry Web Application. 
The web application shows the administration site with all registered microservices. 

```
http://localhost:8761/
```
Test Cardata Microservices:

The Cardata Microservices are registered by the eureka service registry. 
A simple Http-Request on any microservice returned "Hello World Eureka" response.
The results are shown in the console and console log via the logging srcipt. 

```
curl http://localhost:8081/helloWorld/
curl http://localhost:8082/helloWorld/
curl http://localhost:8083/helloWorld/
```

Test Maintenance Microservice:

The Maintenance Microservice is a microservice client and able to discover the eureka 
service registry. To see all running cardata microservice instances call the following command. 

```
curl http://localhost:8081/instance/
```

To test the load balancing feature you can call 


```
curl http://localhost:8081/workload/{count}/{type}
```

The count parameter stand for the amount of microservice calls.
The type can be one (1). One is an nature synchrone call with 
the Rest Template. 


Test The API Gateway Service: 

Finally you can test the API Gateway Microservice. 

```
curl -X GET http://localhost:8090/cardata/helloWorld -u michael:nix
```

The Zuul Proxy is secured. A user and password is necessary.  


##Run the Services in the Cloud Foundry 

##Run the Microservices in a docker container 






