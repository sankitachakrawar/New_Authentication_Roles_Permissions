package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.UserCount;
import com.example.entities.UserEntity;
import com.example.entities.UserTemp;
import com.example.helper.ExcelHelper;
import com.example.repository.ExcelRepository;
import com.example.repository.UserCountRepository;
import com.example.repository.UserRepository;
import com.example.service.ExcelService;
import com.example.utils.JwtTokenUtil;

@Service
@Transactional
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

//			this.excelRepository.saveAll(temp);

			// to save countId in UserTemp(temporary) table
			for (int j = 0; j < temp.size(); j++) {

				UserTemp userTemp = new UserTemp();
				userTemp.setEmail(temp.get(j).getEmail());
				userTemp.setName(temp.get(j).getName());
				userTemp.setAddress(temp.get(j).getAddress());
				userTemp.setUsername(temp.get(j).getUsername());
				userTemp.setCountid(count.getCountid());
				excelRepository.save(userTemp);
			}

			int i = 0;
			System.out.println("HER  ");

			List<UserTemp> temp4 = this.excelRepository.getByCountid(count.getCountid());
			System.out.println("temp4>>  " + temp4.size());

			// for loop for save one by one data in users(main) table

			for (int j = 0; j < temp4.size(); j++) {

				UserEntity entity = new UserEntity();

				entity.setName(temp4.get(j).getName());
				entity.setEmail(temp4.get(j).getEmail());
				entity.setAddress(temp4.get(j).getAddress());
				entity.setUsername(temp4.get(j).getUsername());

				try {

					// for avoid duplicate record
					email = temp4.get(j).getEmail();

					Optional<UserEntity> dataBaseEmail = userRepository.findByEmailContainingIgnoreCase(email);

					if ((null == dataBaseEmail) || dataBaseEmail.isEmpty()) {

						this.userRepository.save(entity);

						UserTemp temp2 = temp4.get(j);
						temp2.setStatus(true);
						excelRepository.save(temp2);

					} else {

						UserTemp temp2 = temp4.get(j);
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

	// fetch list of users
	@Override
	public List<UserTemp> getAllUsers() {

		return this.excelRepository.findAll();

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