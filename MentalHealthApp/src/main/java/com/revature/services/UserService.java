package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.daos.UserDao;
import com.revature.models.User;

@Service
public class UserService {
	
	UserDao UDao = new UserDao();
	
	// add new user
	public User addUser(User user) {
		return UDao.insertUser(user);
	}
	
	public User findUserByUsername(String username) {
		return UDao.findUserByUsername(username);
	}
	
	public User findUserById(int id) {
		return UDao.findUserById(id);
	}
}
