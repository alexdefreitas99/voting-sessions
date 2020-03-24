FROM gradle:6.2.2-jdk11 as builder

RUN mkdir /app
WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY src/ ./src
COPY voting-sessions-contract/ ./voting-sessions-contract
COPY voting-sessions-domain/ ./voting-sessions-domain
COPY voting-sessions-integration/ ./voting-sessions-integration
COPY voting-sessions-job/ ./voting-sessions-job
COPY voting-sessions-queue/ ./voting-sessions-queue

RUN gradle clean build

##################################################################
FROM openjdk:jdk

COPY --from=builder /app /app
WORKDIR /app

EXPOSE 8081

CMD /app/build/install/voting-sessions/bin/voting-sessions