package com.example.controller;


import java.io.IOException;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.SuccessResponseDto;
import com.app.dto.UserDataDto;
import com.example.entities.UserEntity;
import com.example.entities.UserTemp;
import com.example.helper.ExcelHelper;
import com.example.service.ExcelService;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelService excelService;
	
	
	//upload data from excel file to database
	@PostMapping()
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
		if(ExcelHelper.checkExcelFormat(file)) {
			
			this.excelService.save(file);
			return ResponseEntity.ok(Map.of("Message","file uploaded and save to db"));
			
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file only");
	}
	
	@GetMapping()
	public List<UserTemp> getAllUsers(){
		return this.excelService.getAllUsers();
	}
	
	
	
	
}
