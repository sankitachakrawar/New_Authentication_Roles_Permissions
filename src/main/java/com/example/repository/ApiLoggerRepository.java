package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ApiLogger;

public interface ApiLoggerRepository extends JpaRepository<ApiLogger, Long>{

}
