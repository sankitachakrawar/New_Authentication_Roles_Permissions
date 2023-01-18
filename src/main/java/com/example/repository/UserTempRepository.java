package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.UserEntity;
import com.example.entities.UserTemp;


public interface UserTempRepository extends JpaRepository<UserTemp, Long> {

	List<UserTemp> findByCountId(Long countId);

}
