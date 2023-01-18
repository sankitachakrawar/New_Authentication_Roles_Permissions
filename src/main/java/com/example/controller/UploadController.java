package com.example.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.ErrorResponseDto;
import com.example.dto.SuccessResponseDto;

@RestController
@RequestMapping("/upload")
public class UploadController implements ServletContextAware{

	private ServletContext servletContext;
	
	@PostMapping()
	public ResponseEntity<?> uploadFile(@RequestParam MultipartFile[] files, HttpServletRequest request){
		try {
			for(MultipartFile file:files) {
				System.out.println("File Name:"+file.getOriginalFilename());
				System.out.println("File Size:"+file.getSize());
				System.out.println("File Type:"+file.getContentType());
				System.out.println("------------------------");
				save(file);
			}
			return new ResponseEntity<>(new SuccessResponseDto("Files uploaded successfully","FileUploadedSuccessfully",""),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Check files","CheckFiles"),HttpStatus.BAD_REQUEST);
		}
	}
	
	public String save(MultipartFile file){
		try {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("ddMMyyyyHHmmss");
			String newFileName =simpleDateFormat.format(new Date())+file.getOriginalFilename();
			byte[] bytes =file.getBytes();
			Path path = Paths.get(this.servletContext.getRealPath("/upload"+newFileName));
			Files.write(path, bytes);
			return newFileName;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
		
	}
}
