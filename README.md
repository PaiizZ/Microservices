# Microservices Using Spring Boot and Docker

Create .jar at MicroserviceConfigServer, EmployeeEurekaServer, EmployeeSearchService and EmployeeDashBoardService Folder
```
mvn package
```
then remane the .jar file by remove **-0.0.1-SNAPSHOT**

#### Go back to Microservices-Using-Spring-Boot-and-Docker Folder

build alpine-jdk
```
docker build --tag=alpine-jdk:base --rm=true .
```

run docker-compose.yml
```
docker-compose up --build
```

Ref. https://dzone.com/articles/buiding-microservice-using-springboot-and-docker
