package com.example.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.UserTemp;

public class ExcelHelper {

	//check file is of excel file or not
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType=file.getContentType();
		
		if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}else {
			return false;
		}
	}
	
	//convert excel to list of users
	
	public static List<UserTemp> convertExcelToListOfUsers(InputStream is){
	
		List<UserTemp> temp=new ArrayList<>();
		
		try {
			
		 XSSFWorkbook workbook=new XSSFWorkbook(is);
		 //sheet1 is excel sheetname in excel
		 XSSFSheet sheet=workbook.getSheet("excel1");
		
		 int rowNumber=0;
		 Iterator<Row> iterator=sheet.iterator();
		 while(iterator.hasNext()) {
			 Row row=iterator.next();
			 
			 if(rowNumber==0) {
				 rowNumber++;
				 continue;
			 }
			 Iterator<Cell> cell=row.iterator();
			 int cid=0;
			 
			 UserTemp temp2=new UserTemp();
			 
			 while(cell.hasNext()) {
				Cell cells= cell.next();
				
				switch (cid) {
				case 0:
					temp2.setId((long)cells.getNumericCellValue());
					break;
				case 1:
					temp2.setName(cells.getStringCellValue());
					break;
				case 2:
					temp2.setEmail(cells.getStringCellValue());
					break;
				case 3:
					temp2.setUsername(cells.getStringCellValue());
					break;
				case 4:
					temp2.setAddress(cells.getStringCellValue());
					break;
				default:
					break;
				}
				cid++;
				
				
			 }
			 temp.add(temp2);
		 }
		 
		 
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return temp;
		
	}
}
