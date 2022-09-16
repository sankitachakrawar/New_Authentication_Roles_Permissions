package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.UserCount;

public interface CountRepository extends JpaRepository<UserCount, Long> {

	void save(String email);

}
