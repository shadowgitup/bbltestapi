# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Expose the port the application will run on
EXPOSE 8088

# Command to run the application
CMD ["java", "-jar", "myapp.jar"]