# Microservices Using Spring Boot and Docker

build alpine-jdk at MicroserviceConfigServer folder
```
docker build --tag=alpine-jdk:base --rm=true .
```

run docker-compose.yml at Microservice folder
```
docker-compose up --build
```
