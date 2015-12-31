## spring-microservices


What this project is about....

#Quickstart

##Prerequisites:
- Java SE 8
- git
- maven
- bash

##Clone repository:
`````
git clone https://github.com/Thinkenterprise/spring-microservices.git
cd spring-microservices
```

##Build Microservices:

```
./buildservices.sh 
```

##Start Microservice Logging:

```
./logservices.sh 
```



##Start Microservices:
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
A simple Http-Request to each Microservice with Hello World Eureka response.
The results are shown in the console an console log over the logging srcipt. 

```
curl http://localhost:8081/helloWorld/
curl http://localhost:8082/helloWorld/
curl http://localhost:8083/helloWorld/
```

Test Maintenance Microservice:

The Maintenance Microservice is a Microservice client and able to discover the eureka 
service registry. Call the following command to the all carda service which are found 
by the maintenance service. 

```
curl http://localhost:8081/instance/
```

```
git clone https://github.com/Thinkenterprise/spring-microservices.git
cd spring-projections
mvn clean package spring-boot:run
```



