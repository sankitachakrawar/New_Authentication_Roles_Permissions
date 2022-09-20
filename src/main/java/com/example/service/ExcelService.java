package com.example.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.entities.UserTemp;

public interface ExcelService {

	public void save(MultipartFile file, HttpServletRequest request) throws Exception;

	public List<UserTemp> getAllUsers();

}
