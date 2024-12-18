package com.example.mrspobacked.entities;

import com.example.mrspobacked.converters.JsonListAttributeConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity
@Table(name = "user_actions")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserActionEntity extends BaseEntity{
    Long userId;

    String path;

    String requestType;

    String methodName;

    @Convert(converter = JsonListAttributeConverter.class) // Используем наш конвертер для сериализации/десериализации
    @Column(name = "request_params", columnDefinition = "TEXT") // Храним как строку JSON
    Collection<Map<String, Object>> requestParams;
}
