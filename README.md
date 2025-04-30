# spatial-laser-backend

## Overview

This Spring Boot application serves as the backend for the Spatial Laser project, a geospatial property management system with zoning type modification capabilities. It provides REST APIs to retrieve property data with their geographic boundaries as GeoJSON polygons and allows updating zoning classifications while maintaining an audit trail of changes.

## Architecture

### Dual Database Design

The application employs a strategic dual-database architecture:

- Read-only Database (Property Data): Contains geospatial property information accessed through a PostgreSQL database with PostGIS extension
- Writable Database (Zoning Updates): Stores zoning type modifications and audit logs in a separate PostgreSQL database

This separation ensures the original property data remains untouched while providing robust update capabilities.

Read-only Database (Property Data): Contains geospatial property information accessed through a PostgreSQL database with PostGIS extension
Writable Database (Zoning Updates): Stores zoning type modifications and audit logs in a separate PostgreSQL database
This separation ensures the original property data remains untouched while providing robust update capabilities.

Key Features

## Key Features

- Geospatial Queries: Fetch properties within geographical bounding boxes
- Zoning Type Management: Update zoning types for multiple properties simultaneously
- Audit Logging: Track all zoning modifications for compliance and review
- GeoJSON Support: Full support for geospatial data in standard GeoJSON format
- Swagger Documentation: Interactive API documentation and testing

## Technical Stack

- Framework: Spring Boot 3.4.5
- Java Version: Java 17
- Database:
  - PostgreSQL with PostGIS extension (property data)
  - PostgreSQL (zoning updates and audit logs)
- Spatial Libraries:
  - JTS (Java Topology Suite)
  - Jackson-datatype-jts for JSON serialization
- Documentation: SpringDoc OpenAPI (Swagger UI)
- Deployment: Docker with GitHub Actions to Render

## CORS Configuration

This API implements cross-origin resource sharing (CORS) to secure access to endpoints.

### Production Configuration

Controllers are configured with the `@CrossOrigin` annotation specifying the production frontend:

```java
@CrossOrigin(origins = "https://spatial-laser-frontend.onrender.com")
```

### Development Environment

For local development, modify the origin in `PropertyController.java`:

```java
@CrossOrigin(origins = "http://localhost:3000") // Your local frontend URL
```

### Multiple Environments

To support both production and development simultaneously:

```java
@CrossOrigin(origins = {
  "https://spatial-laser-frontend.onrender.com",
  "http://localhost:3000"
})
```

## Getting Started

### Prerequisites

- JDK 17+

### Environment Configuration

Create a `.env` file based on the `.env.sample` template:

```properties
# Property Database Configuration
DB_PROPERTY_URL=jdbc:postgresql://<host>:<port>/<database>
DB_PROPERTY_USERNAME=<username>
DB_PROPERTY_PASSWORD=<password>

# Zoning Database Configuration
DB_ZONING_URL=jdbc:postgresql://<host>:<port>/<database>
DB_ZONING_USERNAME=<username>
DB_ZONING_PASSWORD=<password>
```

### Running Locally

```bash
# Build the project
./mvnw clean package

# Run the application
./mvnw spring-boot:run

# Alternatively, run directly without separate build step
./mvnw spring-boot:run -DskipTests
```

## Documentation & Endpoints

- **Swagger UI**: Access interactive API documentation at `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: Available at `http://localhost:8080/api-docs.json`

## Project Structure

```
com.spatiallaser.backend
├── config/       # Data source configurations and serialization settings
├── controller/   # REST API endpoints
├── dto/          # Request/response data transfer objects
├── entity/
│   ├── reading/  # Entities mapped to the read-only property database
│   └── writing/  # Entities mapped to the writable zoning database
├── enums/        # Enumerated types including zoning classifications
├── repository/   # Data access interfaces for both databases
└── service/      # Business logic for property and zoning operations
```

## Database Migration

The application uses Flyway to manage database migrations for the writable database. Migration scripts are located in `src/main/resources/db/migration`.

## Deployment

Deployment to Render.com is automated via GitHub Actions. The workflow can be found in `.github/workflows` and can be triggered manually from the GitHub repository.
