@echo off
call mvn clean package
call docker build -t com.mycompany/T02 .
call docker rm -f T02
call docker run -d -p 9080:9080 -p 9443:9443 --name T02 com.mycompany/T02