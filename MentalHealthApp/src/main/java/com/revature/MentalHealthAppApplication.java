package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.daos.EntryDao;
import com.revature.daos.UserDao;
import com.revature.models.Entry;
import com.revature.models.User;

@SpringBootApplication
public class MentalHealthAppApplication {

	public static void main(String[] args) {
		// SpringApplication.run(MentalHealthAppApplication.class, args);
		
		UserDao UDao = new UserDao();
		EntryDao EDao = new EntryDao();
		
		User u1 = new User("user1", "p@ssw0rd", false, "Joe", "Smith", "js@yahoo.com");
		User u2 = new User("user2", "p@ssw0rd", true, "Sam", "Parker", "sp@yahoo.com");
		User u3 = new User("user3", "password", false, "Bob", "Bobby", "bb@yahoo.com");
		User u4 = new User("user4", "abcdefg", true, "John", "Jacob", "jj@yahoo.com");
		
		UDao.insertUser(u1);
		UDao.insertUser(u2);
		UDao.insertUser(u3);
		UDao.insertUser(u4);
		
		UDao.addPatient(u2, u1);
		
		// adding entry to patient
		Entry e1 = new Entry("My Title 1", "This is my entry body.", false, "#tag1, #tag2", 1);
		Entry persistedEntry1 = EDao.insertEntry(e1, u1);
		
		Entry e2 = new Entry("My Title 2", "This is my entry body.", false, "#tag1, #tag2", 1);
		Entry persistedEntry2 = EDao.insertEntry(e2, u1);
		
		
		
//		System.out.println(u1);
//		System.out.println(u2);
//		System.out.println(u3);
//		
//		User user1 = UDao.findUserById(u1.getId());
//		User user2 = UDao.findUserById(u2.getId());
//		User user3 = UDao.findUserById(u3.getId());
//		
//		
//		System.out.println("U1's assigned profs.");
//		System.out.println(u1.getAssignedProfessionals());
//		
//		System.out.println("U2's assigned patients");
//		System.out.println(u2.getAssignedPatients());
//		
//		System.out.println("U3's assigned profs.");
//		System.out.println(u3.getAssignedProfessionals());
//		
//		System.out.println(UDao.findAllAssignedPatients(u2.getId()));
//		System.out.println(UDao.findAllAssignedProfessionals(u1.getId()));
//		
//		System.out.println(UDao.findAllAssignedPatients(u1));
//		System.out.println("Trying to get patients from a patient");
		
		
	}

}
