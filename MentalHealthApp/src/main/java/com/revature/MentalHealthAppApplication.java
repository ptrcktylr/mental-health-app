package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.daos.EntryDao;
import com.revature.daos.ReplyDao;
import com.revature.daos.UserDao;
import com.revature.models.Entry;
import com.revature.models.Reply;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.PatientService;
import com.revature.services.ProfessionalService;
import com.revature.services.UserService;

@SpringBootApplication
public class MentalHealthAppApplication {

	public static void main(String[] args) {
		// SpringApplication.run(MentalHealthAppApplication.class, args);
		
//		UserDao UDao = new UserDao();
//		EntryDao EDao = new EntryDao();
//		ReplyDao RDao = new ReplyDao();
		
		PatientService pts = new PatientService();
		ProfessionalService prs = new ProfessionalService();
		LoginService ls = new LoginService();
		UserService us = new UserService();
		
		User u1 = new User("user1", "p@ssw0rd", false, "Joe", "Smith", "js@yahoo.com");
		User u2 = new User("user2", "p@ssw0rd", true, "Sam", "Parker", "sp@yahoo.com");
		User u3 = new User("user3", "password", false, "Bob", "Bobby", "bb@yahoo.com");
		User u4 = new User("user4", "abcdefg", true, "John", "Jacob", "jj@yahoo.com");
				
		us.addUser(u1);
		us.addUser(u2);
		us.addUser(u3);
		us.addUser(u4);
		
	}

}
