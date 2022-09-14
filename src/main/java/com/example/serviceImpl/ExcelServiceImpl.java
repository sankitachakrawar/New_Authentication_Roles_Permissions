package com.example.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.FileUploadEntity;
import com.example.entities.UserTemp;
import com.example.helper.ExcelHelper;
import com.example.repository.ExcelRepository;
import com.example.service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService{

	@Autowired
	private ExcelRepository excelRepository;
	
	@Override
	public void save(MultipartFile file) {
		try {
			List<UserTemp> temp=ExcelHelper.convertExcelToListOfUsers(file.getInputStream());
			
			this.excelRepository.saveAll(temp);
			
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public List<UserTemp> getAllUsers() {
		
		return this.excelRepository.findAll();
	}

	
	
	
	

	
	

	
	
}
