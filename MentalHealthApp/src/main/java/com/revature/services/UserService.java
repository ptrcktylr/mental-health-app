package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class UserService {
	
	UserDao UDao = new UserDao();
	
	// add new user
	public User addUser(User user) {
		return UDao.insertUser(user);
	}
	
}
