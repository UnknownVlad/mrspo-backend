package com.example.mrspobacked.servises;

import com.example.mrspobacked.entities.UserActionEntity;
import com.example.mrspobacked.repositories.UserActionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserActionService {
    private final UserActionRepository userActionRepository;

    public UserActionEntity createAndSaveUserAction(Long userId, String path, String requestType, String methodName, Map<String, Object> requestParams) {
        return saveAction(
                UserActionEntity.builder()
                        .userId(userId)
                        .path(path)
                        .requestType(requestType)
                        .methodName(methodName)
                        .requestParams(List.of(requestParams))
                        .build()
        );
    }

    public UserActionEntity saveAction(UserActionEntity userActionEntity) {
        return userActionRepository.save(userActionEntity);
    }

    public List<UserActionEntity> getUserActions(Long userId) {
        return userActionRepository.findByUserId(userId);
    }

    public List<UserActionEntity> getUserActions(Long userId, String requestType) {
        return userActionRepository.findByUserIdAndRequestType(userId, requestType);
    }

}
