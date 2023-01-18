package com.example.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public interface ExcelFileService {

	void save(XSSFWorkbook workbook, String fileName, HttpServletRequest request);
}
