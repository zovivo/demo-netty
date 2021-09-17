# demo-netty
Demo Netty Spring Boot

1. Module netty-client exchange Module netty-server via socketChannel
2. Module netty-server exchange Module process-transaction via RabbitMQ
3. Request from netty-client, send to netty-server, and then send to process-transaction
4. Exchange between client and server is pack and unpack by ISO 8583