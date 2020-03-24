# Voting session
[![Build Status](https://travis-ci.org/alexdefreitas99/voting-sessions.svg?branch=master)](https://travis-ci.org/alexdefreitas99/voting-sessions)

This is a Java project to manage member voting sessions.

Simple Spring Boot server application developed with Spring Boot 2.1.7 and Java OpenJDK 11.

# How to use the API (Application Programming Interface)
## Step 1: 
Create a agenda: 
Request:
[POST] http://localhost:8081/voting-sessions/v1/agenda
```
{ "subject": "Agenda testing vote" }
```
Response: 
```
{
  "id": 4,
  "subject": "Agenda testing vote",
  "votesInFavor": 0,
  "votesAgainst": 0
}
```

## Step 2:
Create a voting session to this agenda:
Request:
[POST] http://localhost:8081/voting-sessions/v1/session/agenda/4
```
{"minuteDuration": 3 }
```
Response: 
```
{
  "sessionId": 10,
  "agendaId": 4,
  "openingDate": "2020-03-23T22:25:19.628266678",
  "closingDate": "2020-03-23T22:28:19.622715976"
}
```

## Step: 3
Vote:

Request:
[POST] http://localhost:8081/voting-sessions/v1/voting/session/10/agenda/4
```
{
	"associatedCpf": "54125851085",
	"vote": true
}
```
Response:
```
{
	"id": 20
}
```

## To see the total votes in a agenda 
[GET] http://localhost:8081/voting-sessions/v1/agenda/4
Response: 
```
{
  "id": 4,
  "subject": "Agenda testing vote",
  "votesInFavor": 15,
  "votesAgainst": 6
}
```

# Bonus

## To see the total votes of the session agendas.
Obs: The result of each session will only be available after closing
$ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic agenda.queuing --from-beginning

# Documentation
## Swagger
http://localhost:8081/voting-sessions/swagger-ui.html

# Quality
## SonarQube
$ docker pull sonarqube && docker run -d --name sonarqube -p 9000:9000 sonarqube

$ ./gradlew -Dsonar.host.url=http://localhost:9000 sonarqube
