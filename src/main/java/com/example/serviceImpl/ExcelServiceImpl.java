package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.entities.UserEntity;
import com.example.entities.UserTemp;
import com.example.helper.ExcelHelper;
import com.example.repository.ExcelRepository;
import com.example.repository.UserRepository;
import com.example.service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService{

	@Autowired
	private ExcelRepository excelRepository;
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void save(MultipartFile file) {
		try {
			List<UserTemp> temp=ExcelHelper.convertExcelToListOfUsers(file.getInputStream());
			
			this.excelRepository.saveAll(temp);
			
			List<UserTemp> list=this.excelService.getAllUsers();
			System.out.println("List>>   "+list);
			
			int i;
			for(i=0;i< list.size();i++) {
				
				UserEntity entity=new UserEntity();
				
				entity.setName(list.get(i).getName());
				entity.setEmail(list.get(i).getEmail());
				entity.setAddress(list.get(i).getAddress());
				entity.setUsername(list.get(i).getUsername());
				try {
					
					String email=list.get(i).getEmail();
				
					Optional<UserEntity> dataBaseEmail = userRepository.findByEmailContainingIgnoreCase(email);
					
					if ((dataBaseEmail == null) || dataBaseEmail.isEmpty()) {
						
						this.userRepository.save(entity);
						
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				UserTemp temp2=list.get(i);
				temp2.setStatus(true);
				excelRepository.save(temp2);
				
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public List<UserTemp> getAllUsers() {
		
		return this.excelRepository.findAll();
	}	
	
}

