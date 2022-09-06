#!/bin/sh
mvn clean package && docker build -t com.mycompany/T02 .
docker rm -f T02 || true && docker run -d -p 9080:9080 -p 9443:9443 --name T02 com.mycompany/T02