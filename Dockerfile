# Use a lightweight OpenJDK runtime image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy the built JAR file (adjust name if needed)
COPY target/iot-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render sets PORT environment variable, we'll use it)
EXPOSE 8080

# Let Spring Boot pick up the port from env
ENV PORT=8080
ENV JAVA_OPTS=""

# Entry point
CMD ["sh", "-c", "java $JAVA_OPTS -Dserver.port=$PORT -jar app.jar"]
