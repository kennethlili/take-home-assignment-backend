FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

# Copy maven/gradle files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Build dependencies
RUN ./mvnw dependency:go-offline -B
# OR for Gradle
# RUN ./gradlew dependencies

# Copy source code
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Use smaller runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /workspace/app/target/*.jar app.jar

# Set environment variables
ENV PORT=8080

# Expose the port
EXPOSE ${PORT}

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]