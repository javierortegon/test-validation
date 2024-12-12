package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class JsonSchemaValidationService {

    @Autowired
    private JsonSchema jsonSchema;

    public List<ResponseData> validateJson(JsonNode jsonNode) {

        List<ResponseData> messageErros = new ArrayList<>();
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        //if errors have a single miss match, there would be a value in the errors set.
        if (errors.isEmpty()) {
            //event is valid.
            log.info("event is valid");
        } else {
            //event is in_valid.
            log.info("event is invalid");

            errors.forEach(error -> {
                messageErros.add(ResponseData.builder()
                        .fieldName(String.valueOf(error.getInstanceLocation()).replaceAll("\\$\\.(\\w+)", "$1"))
                        .messageError(error.getMessage().replaceFirst(".*?:\\s*", ""))
                        .build());
            });

        }
        return messageErros;
    }
}
