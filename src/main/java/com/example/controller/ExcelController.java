package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.ErrorResponseDto;
import com.example.dto.SuccessResponseDto;
import com.example.entities.UserTemp;
import com.example.helper.ExcelHelper;
import com.example.service.ExcelService;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	// upload data from excel file to database
	@PostMapping("/uploadFile")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {

		if (ExcelHelper.checkExcelFormat(file)) {

			this.excelService.save(file, request);

			return new ResponseEntity<>(new SuccessResponseDto("file uploaded successfully","fileUploadedSuccessfully", null), HttpStatus.OK);
		}

		return new ResponseEntity<>(new ErrorResponseDto("Please upload excel file","PleaseUploadExcelFile"), HttpStatus.BAD_REQUEST);
	}

	@GetMapping()
	public List<UserTemp> getAllUsers() {
		return this.excelService.getAllUsers();
	}

}
