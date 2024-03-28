#FROM openjdk:21-jdk-slim AS build
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the packaged jar file into the container
#COPY target/api.jar /app
#
## Define the command to run the application
#CMD ["java", "-jar", "api.jar"]

# Use a multi-stage build
FROM gradle:jdk21 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the project files
COPY . .

# Build the JAR file
RUN gradle build --no-daemon

# Use a smaller base image for the final image
FROM openjdk:21-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the builder stage
COPY --from=builder /app/build/libs/api.jar /app

# Define the command to run the application
CMD ["java", "-jar", "api.jar"]
