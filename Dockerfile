# Use Maven to build the application
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Set working directory for the build stage
WORKDIR /app

# Copy pom.xml and source files into the container
COPY pom.xml ./
COPY src ./src

# Build the project, skipping tests
RUN mvn clean package -DskipTests

# Final image for running the application
FROM openjdk:21-jdk-slim

# Install wget with minimal packages and clean up after installation
RUN apt-get update \
    && apt-get install -y --no-install-recommends wget \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the JAR file built in the previous stage to the container
COPY --from=build /app/target/inditex-0.0.1-SNAPSHOT.jar /app/inditex-0.0.1-SNAPSHOT.jar

# Copy the healthcheck script into the container
COPY healthcheck.sh /usr/local/bin/healthcheck.sh

# Create a user and group for the application with restricted permissions
# Make the healthcheck script executable and change the ownership to the user created
# Change ownership of the /app directory and all its contents
RUN addgroup --system inditex_group && \
    adduser --system --group inditex_user && \
    chmod +x /usr/local/bin/healthcheck.sh && \
    chown inditex_user:inditex_group /usr/local/bin/healthcheck.sh && \
    chown -R inditex_user:inditex_group /app

# Switch to the non-root user to run the application
USER inditex_user

# Expose the port the application will run on
EXPOSE 3000

# Set the Docker HEALTHCHECK using the healthcheck script
HEALTHCHECK --interval=30s --timeout=10s --retries=3 --start-period=30s \
    CMD ["/usr/local/bin/healthcheck.sh"]

# Set the entrypoint to run the JAR file with Java
ENTRYPOINT ["java", "-jar", "/app/inditex-0.0.1-SNAPSHOT.jar"]
