# Producer

Example of producer as a part of Kafka Event driven design pattern.
Basically it is just a Spring boot app, which load a customer from a MySQL database and produce them as a message to the
kafka broker.
Runs as a microservice within a container.