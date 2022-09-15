package com.example.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import com.example.dto.ErrorResponseDto;
import com.example.entities.ApiLogger;
import com.example.entities.LoggerEntity;
import com.example.service.ApiLoggerService;
import com.example.service.LoggerServiceInterface;
import com.google.gson.Gson;


@Component
public class ApiLoggerInterceptor implements HandlerInterceptor{

	@Autowired
	private ApiLoggerService apiLoggerService;
	
	@Autowired
	private LoggerServiceInterface loggerServiceInterface;
	
	Gson gson = new Gson();
	
	public ApiLoggerInterceptor() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String[] arr = request.getRequestURI().split("/");
		String getParam = arr[arr.length - 1];
		String getParam2 = arr[arr.length - 2];
		
		ArrayList<String> skipUrls = new ArrayList<>(Arrays.asList("/auth/register","/auth/login","/api/forgot-pass-confirm","/auth/forgot-pass","/api/orders","/payment/success", "/file/downloadFile/" + getParam2 + "/" + getParam));
		if (!skipUrls.contains(request.getRequestURI())) {

			final String requestTokenHeader = request.getHeader("Authorization").split(" ")[1];
		
		LoggerEntity logsDetail = loggerServiceInterface.getLoggerDetail(requestTokenHeader);

		if (logsDetail == null) {

			ErrorResponseDto error = new ErrorResponseDto("You are not login User", "notLoginUser");
			String employeeJsonString = this.gson.toJson(error);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(employeeJsonString);
			return false;

		} else {
		
		
		ApiLogger apiLogger=new ApiLogger();
		
		apiLogger.setMethod(request.getMethod());
		apiLogger.setUrl(request.getRequestURI());
		apiLogger.setToken(request.getHeader("Authorization"));
		apiLogger.setBody(request instanceof StandardMultipartHttpServletRequest ? null : request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		apiLoggerService.createApiLogger(apiLogger);
		return true;
		}
	}else {

		return true;

	
		}
	}
	
}
