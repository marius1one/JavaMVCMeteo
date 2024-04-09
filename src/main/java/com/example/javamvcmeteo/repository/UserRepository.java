package com.example.javamvcmeteo.repository;

import com.example.javamvcmeteo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    UserEntity findByUsername(String username);
}
