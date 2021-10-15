package com.revature.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		Object loginedUsername = session.getAttribute("loginedUser");
		if(loginedUsername != null){
			
			return true;
			
		}else {
		
		request.setAttribute("msg","Please login!");
		request.getRequestDispatcher("/login").forward(request,response);
		
		return false;
	}		
 }
}
