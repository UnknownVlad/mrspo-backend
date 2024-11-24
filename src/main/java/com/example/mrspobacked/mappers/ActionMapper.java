package com.example.mrspobacked.mappers;

import com.example.mrspobacked.controllers.dtos.common.UserActionDto;
import com.example.mrspobacked.entities.UserActionEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActionMapper {
    private final ObjectMapper objectMapper;

    public UserActionDto toDto(UserActionEntity userActionEntity) {
        try {
            return UserActionDto.builder()
                    .userId(userActionEntity.getUserId())
                    .path(userActionEntity.getPath())
                    .requestType(userActionEntity.getRequestType())
                    .methodName(userActionEntity.getMethodName())
                    .requestParams(objectMapper.writeValueAsString(userActionEntity.getRequestParams()))

                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
