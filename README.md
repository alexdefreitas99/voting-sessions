# Voting session
[![Build Status](https://travis-ci.org/alexdefreitas99/voting-sessions.svg?branch=master)](https://travis-ci.org/alexdefreitas99/voting-sessions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=alexdefreitas99_voting-sessions&metric=alert_status)](https://sonarcloud.io/dashboard?id=alexdefreitas99_voting-sessions)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=alexdefreitas99_voting-sessions&metric=coverage)](https://sonarcloud.io/dashboard?id=alexdefreitas99_voting-sessions)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=alexdefreitas99_voting-sessions&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=alexdefreitas99_voting-sessions)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=alexdefreitas99_voting-sessions&metric=code_smells)](https://sonarcloud.io/dashboard?id=alexdefreitas99_voting-sessions)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=alexdefreitas99_voting-sessions&metric=bugs)](https://sonarcloud.io/dashboard?id=alexdefreitas99_voting-sessions)

This is a Java project to manage member voting sessions.

Simple Spring Boot server application developed with Spring Boot 2.1.7 and Java OpenJDK 11.

# Technologies used
- **Java 11.** 
- **Spring boot.**
- **Gradle.**
- **MariaDB in AWS cloud.**
- **Apache kafka.**
 
# Project architecture
The project was based on the hexagonal architecture, which consists of dividing an application into layers according to its responsibilities and emphasizing a particular layer.

- **Contract:** This is the main layer and your responsibility is to serve functionality to other applications.
- **Domain:** This layer contains the domain business rule (The if's and else's).
- **Integration:** This is an external service client
- **Job:** Job scheduling that runs programmatically.
- **Queu:** Messaging settings, consumers and senders.

# How to run this project locally

```bash
$ make run # Run the app
```
```bash
$ make docker-stop # Stop docker
```
```bash
$ make docker-build # build docker image
```
```bash
$ make docker-run # run docker image
```

# How to use the API (Application Programming Interface)
## Step 1: 
Create a agenda: 

Request:

[POST] http://localhost:8081/voting-sessions/v1/agenda
```json
{ "subject": "Agenda testing vote" }
```
Response: 
```json
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
```json
{ "minuteDuration": 3 }
```
Response: 
```json
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
```json
{
	"associatedCpf": "54125851085",
	"vote": true
}
```
Response:
```json
{
	"id": 20
}
```

## To see the total votes in a agenda 
[GET] http://localhost:8081/voting-sessions/v1/agenda/4

Response: 
```json
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
```bash
$ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic agenda.queuing --from-beginning
```

# Documentation
## Swagger
http://localhost:8081/voting-sessions/swagger-ui.html

# Quality
## SonarQube
```bash
$ docker pull sonarqube && docker run -d --name sonarqube -p 9000:9000 sonarqube
```

```bash
$ ./gradlew -Dsonar.host.url=http://localhost:9000 sonarqube
```
