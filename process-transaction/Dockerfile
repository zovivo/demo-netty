FROM openjdk:8u141-jre
VOLUME /tmp
ADD target/process-0.0.1-SNAPSHOT.jar proces-transaction-app.jar
ENTRYPOINT exec java -jar proces-transaction-app.jar