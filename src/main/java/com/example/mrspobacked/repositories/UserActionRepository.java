package com.example.mrspobacked.repositories;

import com.example.mrspobacked.entities.UserActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActionRepository extends JpaRepository<UserActionEntity, Long> {

    List<UserActionEntity> findByUserId(Long id);
    List<UserActionEntity> findByUserIdAndRequestType(Long userId, String requestType);

}
