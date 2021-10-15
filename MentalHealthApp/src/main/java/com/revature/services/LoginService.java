package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.daos.UserDao;
import com.revature.models.User;


@Service
public class LoginService {

	public Object userLogin(String username, String password) {
		UserDao UDao = new UserDao();
		return UDao.validLogin(username, password);
	}
	
}
