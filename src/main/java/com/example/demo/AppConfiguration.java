package com.example.demo;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class AppConfiguration {
    private static final String SCHEMA_VALIDATION_FILE = "validation.json";

    @Bean
    public JsonSchema jsonSchema() {
        InputStream inputStream = AppConfiguration.class.getClassLoader().getResourceAsStream(SCHEMA_VALIDATION_FILE);
        if (inputStream == null) {
            throw new IllegalArgumentException("El archivo validation.json no se encontró en el classpath");
        }
        return JsonSchemaFactory
                .getInstance(SpecVersion.VersionFlag.V7)
                .getSchema(inputStream);
    }
}