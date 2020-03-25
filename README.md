# Voting session
[![Build Status](https://travis-ci.org/alexdefreitas99/voting-sessions.svg?branch=master)](https://travis-ci.org/alexdefreitas99/voting-sessions)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=alexdefreitas99_voting-sessions&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=alexdefreitas99_voting-sessions)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=alexdefreitas99_voting-sessions&metric=code_smells)](https://sonarcloud.io/dashboard?id=alexdefreitas99_voting-sessions)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=alexdefreitas99_voting-sessions&metric=bugs)](https://sonarcloud.io/dashboard?id=alexdefreitas99_voting-sessions)

Este é um projeto java para gerenciar pautas, sessões e votações.

# Tecnologias utilizadas
- **Java 11.** 
- **Spring boot 2.1.7.**
- **Gradle 6.2.2.**
- **MariaDB in AWS cloud.**
- **Apache kafka.**
 
# Project architecture
O projeto é baseado na arquitetura hexagonal, que consiste em dividir uma aplicação em camadas de acordo com suas respectivas responsabilidades e dando enfase em uma camada particular.

- **Contract:** Está é a camada principal e sua responsabilidade é de servir operações para outras aplicaçãos utilizando o padrão de comunicação REST.
- **Domain:** Aqui ficam todas as regras de negócios. (The if's and else's).
- **Integration:** Toda comunicação externa deve ficar nesta camada
- **Job:** Agendador de job que roda a cada 30 minutos para verifica todas sessoes que podem ser fechadas, computa os votos e emite um vento para o envio da mensagem para fila.
- **Queu:** Configurações do sistema de mensageria, consumidores e produtores.

**Obs**: O versionamento de api é feito com packages (v1, v2..) dentro do módulo contract

# Documentação
## Swagger
http://localhost:8081/voting-sessions/swagger-ui.html

https://voting-sessions-available.herokuapp.com/voting-sessions/swagger-ui.html

# Como rodar este projeto localmente
```bash
$ make run # Roda o aplicativo
```
```bash
$ make docker-stop # Para a imagem docker
```
```bash
$ make docker-build # Constroe uma imagem docker
```
```bash
$ make docker-run # Roda a imagem docker
```

# Como utilizar esta API (Application Programming Interface)
## Step 1: 
Criando uma pauta: 

Request:

[POST] https://voting-sessions-available.herokuapp.com/voting-sessions/v1/agenda
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
Criando uma sessão de votação para a pauta criada anteriormente.

Request:

[POST] https://voting-sessions-available.herokuapp.com/voting-sessions/v1/session/agenda/4
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
Votando

Request:

[POST] https://voting-sessions-available.herokuapp.com/voting-sessions/v1/voting/session/10/agenda/4
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

## Para ver o total de votos em uma pauta
[GET] https://voting-sessions-available.herokuapp.com/voting-sessions/v1/agenda/4

Response: 
```json
{
  "id": 4,
  "subject": "Agenda testing vote",
  "votesInFavor": 15,
  "votesAgainst": 6
}
```

# Bônus

## Para ver o total de votes de pautas em sessões especificas.
Obs: The result of each session will only be available after closing

**Esta funcionalidade esta disponíbel apenas para quem rodar a aplicação localmente**
```bash
$ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic agenda.queuing --from-beginning
```

# Documentação em inglês:
https://github.com/alexdefreitas99/voting-sessions/blob/master/README.EN.md
