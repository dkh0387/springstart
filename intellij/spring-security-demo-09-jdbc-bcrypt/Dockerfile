FROM maven:3.6.3-jdk-11-slim AS build

# Copy pom.xml to the image
COPY pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml verify clean --fail-never

# Copy the source code 
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml package

# Install Tomcat & openjdk 8 (openjdk has java and javac)
#FROM tomcat:jdk8-openjdk
# Pull tomcat 9.0.39 image
FROM tomcat:9.0.39

# Install vim for editing .xml files
RUN apt-get update && apt-get install -y vim

# Copy source files to tomcat folder structure
COPY --from=build /home/app/target/spring-security-demo-09-jdbc-bcrypt.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

# And run tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]