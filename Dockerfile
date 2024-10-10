# Step 1: Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the Gradle wrapper files and Gradle build scripts
COPY build.gradle settings.gradle gradlew gradlew.bat ./
COPY gradle /app/gradle

# Step 4: Copy the source code and other project files to the container
COPY src /app/src

# Step 5: Download dependencies and build the application
RUN ./gradlew bootJar --no-daemon

# Step 6: Expose the port that the application will run on (default: 8080)
EXPOSE 8080

# Step 7: Set the default command to run the JAR file
CMD ["java", "-jar", "/app/build/libs/java-backend-0.0.1-SNAPSHOT.jar"]