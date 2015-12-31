#!/bin/sh
echo start all  microservices ... 
java -jar ./registry-service/target/registry-service-0.0.1-SNAPSHOT.jar > /dev/null &
sleep 5
echo started service registry ...
java -jar -Dserver.port=8081 -Dspring.profiles.active=eureka ./cardata-service/target/cardata-service-0.0.1-SNAPSHOT.jar > ./cardata-log1.txt &
sleep 3
echo started cardata mciroservice on port 8081 ...
java -jar -Dserver.port=8082 -Dspring.profiles.active=eureka ./cardata-service/target/cardata-service-0.0.1-SNAPSHOT.jar > ./cardata-log2.txt &
sleep 3
echo started cardata microservice on port 8082 ...
java -jar -Dserver.port=8083 -Dspring.profiles.active=eureka ./cardata-service/target/cardata-service-0.0.1-SNAPSHOT.jar > ./cardata-log3.txt &
sleep 3
echo started cardata microservice on port 8083 ...
java -jar -Dserver.port=8085 -Dspring.profiles.active=eureka ./maintenance-service/target/maintenance-service-0.0.1-SNAPSHOT.jar > ./maintenance-service-log.txt &
sleep 3
echo started maintenance service on port 8085 ...
java -jar -Dserver.port=8090 -Dspring.profiles.active=eureka ./api-gateway-service/target/api-gateway-service-0.0.1-SNAPSHOT.jar > ./api-gateway-service-log.txt &
sleep 3
echo started the api gateway microservice on port 8090
