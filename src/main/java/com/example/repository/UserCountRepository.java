package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.UserCount;

public interface UserCountRepository extends JpaRepository<UserCount, Long> {

}
