# Use the OpenJDK 11 base image
FROM openjdk:11

# Set the working directory in the container
WORKDIR /app


COPY ./build/libs/Bproject-1.0-SNAPSHOT.jar /app/Bproject-1.0-SNAPSHOT.jar

# Expose port 8888 for the application
EXPOSE 8888

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/Bproject-1.0-SNAPSHOT.jar"]
