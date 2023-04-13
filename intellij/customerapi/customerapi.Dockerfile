#
# Maven container for building the app
#
FROM maven:3.8.3-openjdk-17 AS build

MAINTAINER de.dkh

# Copy pom.xml to the image
COPY pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml verify clean --fail-never

# Copy the source code
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml package

#
# JDK container for running the app
#
FROM openjdk:17-jdk-slim

RUN apt-get update --fix-missing \
    && apt-get install -y --no-install-recommends netcat \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir /opt/app

# Copy source files to app folder structure
COPY --from=build /home/app/target/customerapi.jar /opt/app/ROOT.jar

COPY ./docker_entrypoint.sh docker_entrypoint.sh
RUN chmod 100 docker_entrypoint.sh
ENTRYPOINT [ "./docker_entrypoint.sh" ]

# And run the app
#CMD ["java", "-jar", "/opt/app/ROOT.jar"]