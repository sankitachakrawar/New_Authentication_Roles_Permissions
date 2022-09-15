package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.UserEntity;
import com.example.entities.UserTemp;

public interface ExcelRepository extends JpaRepository<UserTemp, Long>{



	void save(List<UserTemp> temp);

	Optional<UserEntity> findByEmailContainingIgnoreCase(String email);
	
}
