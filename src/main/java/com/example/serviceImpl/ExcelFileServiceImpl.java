package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.config.KafkaProducer;
import com.example.entities.UserCount;
import com.example.entities.UserEntity;
import com.example.entities.UserTemp;
import com.example.exceptionHandling.ResourceNotFoundException;
import com.example.repository.UserCountRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserTempRepository;
import com.example.service.ExcelFileService;
import com.example.utils.JwtTokenUtil;

@Service
public class ExcelFileServiceImpl implements ExcelFileService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserCountRepository countRepository;
	
	@Autowired
	private UserTempRepository userTempRepository;
	
	@Autowired
	private KafkaProducer kafkaProducer;

	
	@Override
	public void save(XSSFWorkbook workbook, String fileName, HttpServletRequest request) {
		List<UserTemp> tempStudentList = new ArrayList<UserTemp>();

		// TODO Auto-generated method stub
		XSSFSheet worksheet = workbook.getSheetAt(0);

		// for get userId from token
		final String requestTokenHeader = request.getHeader("Authorization");
		String email = null;
		String jwtToken = null;

		jwtToken = requestTokenHeader.substring(7);
		email = jwtTokenUtil.getEmailFromToken(jwtToken);
		UserEntity userEntity = userRepository.getUserByEmail(email);

		// to save userId in UserCount table
		UserCount count = new UserCount();
		count.setId(userEntity);
		countRepository.save(count);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			UserTemp userTemp = new UserTemp();

			XSSFRow row = worksheet.getRow(i);
			
	try {
				userTemp.setName(row.getCell(0).getStringCellValue());
				userTemp.setEmail(row.getCell(1).getStringCellValue());
				userTemp.setUsername(row.getCell(2).getStringCellValue());
				userTemp.setAddress(row.getCell(3).getStringCellValue());
				userTemp.setCountId(count.getCountId());
				
				tempStudentList.add(userTemp);
		} catch (Exception e) {
				throw new ResourceNotFoundException("Data on row " + (i + 1) + " is not in correct format.");
		}

		}
		userTempRepository.saveAll(tempStudentList);
		saveToUserTable(count.getCountId());
			}
	
	private void saveToUserTable(Long countId) {
		List<UserTemp> list = userTempRepository.findByCountId(countId);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("list>>   "+list);
			this.kafkaProducer.addUsersToUsersMainTable(list.get(i));
		}
	}
	

}
