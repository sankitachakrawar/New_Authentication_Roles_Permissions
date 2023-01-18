package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.dto.ErrorResponseDto;
import com.example.dto.SuccessResponseDto;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.service.ExcelFileService;


@RestController
@RequestMapping("/multipleFiles")
public class ExcelFileController {

	@Autowired
	private ExcelFileService excelService;
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		try {
		
				this.excelService.save(workbook,file.getOriginalFilename(), request);

				return new ResponseEntity<>(new SuccessResponseDto("file uploaded successfully","fileUploadedSuccessfully", null), HttpStatus.OK);
			
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), " "),HttpStatus.BAD_REQUEST);
		}
	}
}
