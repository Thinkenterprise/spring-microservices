## spring-microservices
#

What this project is about....

## Quickstart

Prerequisites:
- Java SE 8
- git
- maven
- bash

Clone repository:
`````
git clone https://github.com/Thinkenterprise/spring-microservices.git
cd spring-microservices
```

Build Microservices:

```
./buildservices.sh 
```

Start Microservice Logging:

```
./logservices.sh 
```



Start Microservices:
```
./runservice.sh
```

Simple Tests:

Service Registry Test:
Call the Eureka Service Registry Web Application. 
The web application shows the administration site with all registered microservices. 

```
http://localhost:8761/
```
Test Cardata Microservices:
A simple Http-Request to each Microservice with Hello World Eureka response.
The results are shown in the console an console log over the logging srcipt. 

```
curl http://localhost:8081/helloWorld/
curl http://localhost:8082/helloWorld/
curl http://localhost:8083/helloWorld/
```


```
git clone https://github.com/Thinkenterprise/spring-microservices.git
cd spring-projections
mvn clean package spring-boot:run
```



