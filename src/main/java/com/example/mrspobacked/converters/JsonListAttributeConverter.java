package com.example.mrspobacked.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Converter
public class JsonListAttributeConverter implements AttributeConverter<Collection<Map<String, Object>>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Collection<Map<String, Object>> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute); // Сериализуем List<Map<String, Object>> в строку JSON
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error serializing List<Map<String, Object>> to JSON", e);
        }
    }

    @Override
    public List<Map<String, Object>> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class)); // Десериализуем строку JSON обратно в List<Map<String, Object>>
        } catch (IOException e) {
            throw new IllegalArgumentException("Error deserializing JSON to List<Map<String, Object>>", e);
        }
    }
}