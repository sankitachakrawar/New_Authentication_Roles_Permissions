package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.UserTemp;

public interface ExcelRepository extends JpaRepository<UserTemp, Long>{

	
	
}
