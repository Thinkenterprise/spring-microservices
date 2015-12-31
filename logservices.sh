#!/bin/sh
rm *.txt 
touch cardata-log1.txt
touch cardata-log2.txt
touch cardata-log3.txt
touch maintenance-service-log.txt
touch api-gateway-service-log.txt
tail -f cardata-log1.txt cardata-log2.txt cardata-log3.txt maintenance-service-log.txt api-gateway-service-log.txt
