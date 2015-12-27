#!/bin/sh
echo port:$1 profile:$2
java -jar -Dserver.port=$1 -Dspring.profiles.active=$2 cardata-service-0.0.1-SNAPSHOT.jar
