#
# Gradle container for building the app
#
FROM gradle:8.1.0-jdk17 AS build

MAINTAINER de.dkh

# Copy build.gradle.kts and settings.gradle.kts to the image
#COPY build.gradle.kts /home/gradle/build.gradle.kts
#COPY settings.gradle.kts /home/gradle/settings.gradle.kts

# Copy the source code
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . /home/gradle/src

# Run the build
RUN gradle build --no-daemon

#
# JDK container for running the app
#
FROM openjdk:17-jdk-slim

RUN apt-get update --fix-missing \
    && apt-get install -y --no-install-recommends netcat \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir /opt/app

# Copy source files to app folder structure
COPY --from=build /home/gradle/build/libs/demo-microservices-kafka-0.0.1-SNAPSHOT.jar /opt/app/ROOT.jar

COPY ./docker_entrypoint.sh docker_entrypoint.sh
RUN chmod 100 docker_entrypoint.sh
ENTRYPOINT [ "./docker_entrypoint.sh" ]

# And run the app
#CMD ["java", "-jar", "/opt/app/ROOT.jar"]