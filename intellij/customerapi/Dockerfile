#
# Maven container for building the app
#
FROM maven:3.8.3-openjdk-17 AS build

# Copy pom.xml to the image
COPY pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml verify clean --fail-never

# Copy the source code
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml package

#
# JDK container for running the app
#
FROM eclipse-temurin:17-jdk

RUN mkdir /opt/app

# Copy source files to app folder structure
COPY --from=build /home/app/target/customerapi.jar /opt/app/ROOT.jar

EXPOSE 8081

# And run the app
CMD ["java", "-jar", "/opt/app/ROOT.jar"]