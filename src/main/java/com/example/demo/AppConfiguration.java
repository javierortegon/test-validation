package com.example.demo;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    private static final String SCHEMA_VALIDATION_FILE = "C:/Users/oscortre/Desktop/demo/src/main/resources/validation.json";

    @Bean
    public JsonSchema jsonSchema() {
        return JsonSchemaFactory
                .getInstance( SpecVersion.VersionFlag.V7 )
                .getSchema( getClass().getResourceAsStream( SCHEMA_VALIDATION_FILE ) );
    }
}