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
		
		UserDao udao = new UserDao();
		String username = "jsmith";
		String password = "password";
		
		System.out.println(udao.validLogin(username, password));
		
		SpringApplication.run(MentalHealthAppApplication.class, args);
	}

}
