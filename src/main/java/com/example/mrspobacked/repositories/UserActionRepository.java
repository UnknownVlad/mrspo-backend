package com.example.mrspobacked.repositories;

import com.example.mrspobacked.entities.UserActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepository extends JpaRepository<UserActionEntity, Long> { }
