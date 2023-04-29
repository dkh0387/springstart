#
# Gradle container for building the app
#
FROM gradle:7.5.1-jdk17 AS build

COPY --chown=gradle:gradle . /home/gradle/src/consumer
WORKDIR /home/gradle/src/consumer

RUN gradle build -x test --no-daemon --stacktrace

#
# JDK container for running the app
#
FROM openjdk:17-jdk-slim

RUN apt-get update --fix-missing \
    && apt-get install -y --no-install-recommends netcat \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir /opt/app

# Copy source files to app folder structure
COPY --from=build /home/gradle/src/consumer/build/libs/*.jar /opt/app/consumer.jar

COPY ./docker_entrypoint.sh docker_entrypoint.sh
RUN chmod 100 docker_entrypoint.sh
ENTRYPOINT [ "./docker_entrypoint.sh" ]

# And run the app
#CMD ["java", "-jar", "/opt/app/consumer.jar"]