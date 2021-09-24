FROM openjdk:8u141-jre
VOLUME /tmp
ADD target/netty-server-0.0.1-SNAPSHOT.jar netty-server.jar
ENTRYPOINT exec java -jar netty-server.jar