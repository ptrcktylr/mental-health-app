package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class LoginService {

	public User userLogin(String username, String password) {
		UserDao UDao = new UserDao();
		return UDao.validLogin(username, password);
	}
	
}
