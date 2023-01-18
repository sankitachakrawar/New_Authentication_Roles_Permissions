package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.UserTemp;

public interface ExcelRepository extends JpaRepository<UserTemp, Long> {

	void save(List<UserTemp> temp);

	List<UserTemp> getByCountId(Long countId);

}
