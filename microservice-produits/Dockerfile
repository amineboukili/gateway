#-----------------------------------------------------------------------------------------------------
# Discovery Server
#-----------------------------------------------------------------------------------------------------
# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
MAINTAINER Amine BOUKILI <amine.boukili@ymagis.com>

# Keep consistent with maven
ENV APP_JAR_NAME microservice-produits

# Add JAR file and run it as entrypoint
ADD ${APP_JAR_NAME}.jar /app/
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Make port 9001 available to the world outside this container
EXPOSE 9001