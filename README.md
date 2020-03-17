# Voting session

This is a Java project to manage member voting sessions.

Simple Spring Boot server application developed with Spring Boot 2.2.5 and Java OpenJDK 13.0.2.


# Quality
## SonarQube
$ docker pull sonarqube && docker run -d --name sonarqube -p 9000:9000 sonarqube

$ ./gradlew -Dsonar.host.url=http://localhost:9000 sonarqube
