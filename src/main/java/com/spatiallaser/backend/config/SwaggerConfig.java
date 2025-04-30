package com.spatiallaser.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSchemas("GeoJsonPolygon", new ObjectSchema()
                                .addProperty("type", new StringSchema().example("Polygon"))
                                .addProperty("coordinates", new ArraySchema().items(new ArraySchema().items(new ArraySchema().items(new NumberSchema()))))));

    }
}
