#
# Kafka container for building the Kafka broker/server
#

FROM confluentinc/cp-kafka:latest

#RUN sudo mkdir /usr/local/etc/kafka

# Copy server.properties
COPY server.properties /config/server.properties