FROM gradle:jdk21 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the project files
COPY . .

# Build the JAR file
RUN gradle build --no-daemon

FROM openjdk:21-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the builder stage
COPY --from=builder /app/build/libs/api-1.0.0-SNAPSHOT.jar /app

# Define the command to run the application
CMD ["java", "-jar", "api-1.0.0-SNAPSHOT.jar"]
