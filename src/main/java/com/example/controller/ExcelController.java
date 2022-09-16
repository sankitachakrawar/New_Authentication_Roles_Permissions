package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.UserTemp;
import com.example.helper.ExcelHelper;
import com.example.service.ExcelService;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	// upload data from excel file to database
	@PostMapping()
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception {

		if (ExcelHelper.checkExcelFormat(file)) {

			this.excelService.save(file);

			return new ResponseEntity<>("file uploaded and save to db", HttpStatus.OK);
		}

		return new ResponseEntity<>("Please upload excel file", HttpStatus.BAD_REQUEST);
	}

	@GetMapping()
	public List<UserTemp> getAllUsers() {
		return this.excelService.getAllUsers();
	}
}
