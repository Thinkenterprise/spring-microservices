#!/bin/sh
echo build all projects 
mvn -f ./registry-service/pom.xml clean package
mvn -f ./cardata-service/pom.xml clean package
mvn -f ./maintenance-service/pom.xml clean package
mvn -f ./api-gateway-service/pom.xml clean package
