package com.revature.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.LoginDTO;
import com.revature.services.LoginService;



@RestController
@CrossOrigin
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	
	@PostMapping("/login")
    public String login(@RequestBody LoginDTO user, 
    		            HttpSession session,
    		            Map<String, Object> map) {
		
		 if (loginService.userLogin(user.getUsername(), user.getPassword()) != null){
			 
			 session.setAttribute("loginedUser", user.getUsername());
			 
			 return "redirect:/index.html";	 
		 }else {
			 
			 map.put("msg", "The username or password is incorrect.");
			 map.put("status_code", 401);
			 
			 return "login";
		 }
	}
	
	
	@PostMapping("/logout")
    public String logout(HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        
        return "redirect:/login";
        }
	}


