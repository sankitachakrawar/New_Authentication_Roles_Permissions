package com.example.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.FileUploadEntity;
import com.example.entities.UserCount;
import com.example.entities.UserEntity;
import com.example.entities.UserTemp;
import com.example.helper.ExcelHelper;
import com.example.repository.ExcelRepository;
import com.example.repository.FileStorageRepository;
import com.example.repository.UserCountRepository;
import com.example.repository.UserRepository;
import com.example.service.ExcelService;
import com.example.service.FileStorageService;
import com.example.utils.JwtTokenUtil;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	private ExcelRepository excelRepository;

	@Autowired
	private ExcelService excelService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserCountRepository countRepository;

	@Override
	public void save(MultipartFile file, HttpServletRequest request) throws Exception {
		try {

			List<UserTemp> temp = ExcelHelper.convertExcelToListOfUsers(file.getInputStream());
			this.excelRepository.saveAll(temp);

			List<UserTemp> list = this.excelService.getAllUsers();
			int size = list.size();

			// for get userId from token
			final String requestTokenHeader = request.getHeader("Authorization");
			String email = null;
			String jwtToken = null;

			jwtToken = requestTokenHeader.substring(7);
			email = jwtTokenUtil.getEmailFromToken(jwtToken);

			UserEntity userEntity = userRepository.getUserByEmail(email);
			UserCount count = new UserCount();
			count.setId(userEntity);

			countRepository.save(count);

			UserTemp userTemp = new UserTemp();
			userTemp.setCountid(count.getCountid());
			excelRepository.save(userTemp);

			int i = 0;

			for (i = 0; i < size; i++) {

				UserEntity entity = new UserEntity();

				entity.setName(list.get(i).getName());
				entity.setEmail(list.get(i).getEmail());
				entity.setAddress(list.get(i).getAddress());
				entity.setUsername(list.get(i).getUsername());

				try {

					// for avoid duplicate record
					email = list.get(i).getEmail();

					Optional<UserEntity> dataBaseEmail = userRepository.findByEmailContainingIgnoreCase(email);

					if ((null == dataBaseEmail) || dataBaseEmail.isEmpty()) {

						this.userRepository.save(entity);

						UserTemp temp2 = list.get(i);
						temp2.setStatus(true);
						excelRepository.save(temp2);

					} else {

						UserTemp temp2 = list.get(i);
						temp2.setStatus(false);
						excelRepository.save(temp2);

					}

				} catch (Exception e) {

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UserTemp> getAllUsers() {

		return this.excelRepository.findAll();

	}

	@Autowired
	private FileStorageRepository fileStorageRepository;

	@Autowired
	private FileStorageService fileStorageService;

	@Override
	public void fileBulkUpload(Long fileId, Long userId) throws IOException {

		FileUploadEntity fileDetail = fileStorageRepository.getById(fileId);
		Resource resource = fileStorageService
				.loadFileAsResource(fileDetail.getType() + "/" + fileDetail.getOriginalName());
		ArrayList<UserTemp> tempProjectList = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

			UserTemp project = new UserTemp();
			XSSFRow row = worksheet.getRow(i);

			if (row == null) {

				break;

			}

			project.setName(row.getCell(0).getStringCellValue());
			project.setAddress(row.getCell(0).getStringCellValue());
			project.setEmail(row.getCell(0).getStringCellValue());
			project.setUsername(row.getCell(0).getStringCellValue());
			tempProjectList.add(project);

		}

		excelRepository.saveAll(tempProjectList);

	}

}

//@Override
//public void save(MultipartFile file) throws Exception {
//	try {
//
//		List<UserTemp> temp = ExcelHelper.convertExcelToListOfUsers(file.getInputStream());
//
//		this.excelRepository.saveAll(temp);
//
//		List<UserTemp> list = this.excelService.getAllUsers();
//		
//		int i;
//		
//		
//		
//		for (i = 0; i < list.size(); i++) {
//
//			UserEntity entity = new UserEntity();
//
//			entity.setName(list.get(i).getName());
//			entity.setEmail(list.get(i).getEmail());
//			entity.setAddress(list.get(i).getAddress());
//			entity.setUsername(list.get(i).getUsername());
//
//			try {
//
//				email = list.get(i).getEmail();
//
//				Optional<UserEntity> dataBaseEmail = userRepository.findByEmailContainingIgnoreCase(email);
//
//				if ((null == dataBaseEmail) || dataBaseEmail.isEmpty()) {
//
//					this.userRepository.save(entity);
//
//					UserTemp temp2 = list.get(i);
//					temp2.setStatus(true);
//					excelRepository.save(temp2);
//
//				} else {
//
//					UserTemp temp2 = list.get(i);
//					temp2.setStatus(false);
//					excelRepository.save(temp2);
//
//				}
//
//			} catch (Exception e) {
//
//			}
//
//		}
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//
//}

//List<UserTemp> temp = ExcelHelper.convertExcelToListOfUsers(file.getInputStream());
//this.excelRepository.saveAll(temp);
//
//List<UserTemp> list = this.excelService.getAllUsers();
//int size = list.size();
//
//// for get userId from token
//final String requestTokenHeader = request.getHeader("Authorization");
//String email = null;
//String jwtToken = null;
//
//jwtToken = requestTokenHeader.substring(7);
//email = jwtTokenUtil.getEmailFromToken(jwtToken);
//
//UserEntity userEntity = userRepository.getUserByEmail(email);
//UserCount count = new UserCount();
//count.setId(userEntity);
//
//countRepository.save(count);
//
//UserTemp userTemp = new UserTemp();
//userTemp.setCountid(count.getCountid());
//excelRepository.save(userTemp);
//
//int i = 0;
//
//for (i = 0; i < size; i++) {
//
//	UserEntity entity = new UserEntity();
//
//	entity.setName(list.get(i).getName());
//	entity.setEmail(list.get(i).getEmail());
//	entity.setAddress(list.get(i).getAddress());
//	entity.setUsername(list.get(i).getUsername());
//
//	try {
//
//		// for avoid duplicate record
//		email = list.get(i).getEmail();
//
//		Optional<UserEntity> dataBaseEmail = userRepository.findByEmailContainingIgnoreCase(email);
//
//		if ((null == dataBaseEmail) || dataBaseEmail.isEmpty()) {
//
//			this.userRepository.save(entity);
//
//			UserTemp temp2 = list.get(i);
//			temp2.setStatus(true);
//			excelRepository.save(temp2);
//
//		} else {
//
//			UserTemp temp2 = list.get(i);
//			temp2.setStatus(false);
//			excelRepository.save(temp2);
//
//		}
//
//	} catch (Exception e) {
//
//	}
//}
//
//} catch (Exception e) {
//e.printStackTrace();
//}