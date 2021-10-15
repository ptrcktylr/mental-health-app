//package com.revature.utils;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.revature.interceptor.LoginInterceptor;
//
//@Configuration
//public class AppWebConfiguration implements WebMvcConfigurer{
//	
//	@Override
//    public void addInterceptors(InterceptorRegistry registry) {
//		
//		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/login");
//		
//	}
//}
