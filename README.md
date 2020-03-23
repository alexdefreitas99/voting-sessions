# Voting session
https://travis-ci.org/alexdefreitas99/voting-sessions.svg?branch=master

This is a Java project to manage member voting sessions.

Simple Spring Boot server application developed with Spring Boot 2.1.7 and Java OpenJDK 11.

# Documentation
## Swagger
http://localhost:8081/voting-sessions/swagger-ui.html

# Quality
## SonarQube
$ docker pull sonarqube && docker run -d --name sonarqube -p 9000:9000 sonarqube

$ ./gradlew -Dsonar.host.url=http://localhost:9000 sonarqube
