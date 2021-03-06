package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.LoginDTO;
import com.revature.models.Patient;
import com.revature.models.Professional;
import com.revature.services.LoginService;

@RestController
@CrossOrigin(origins="http://localhost:4200/", allowCredentials="true")
public class LoginController {
	
	// login service
	private LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService
			) {
		super();
		this.loginService = loginService;
	}
	
	@PostMapping("/patient/login")
	public ResponseEntity<Patient> patientLogin(@RequestBody LoginDTO patient, HttpSession session) {
		
		Patient loggedInPatient = loginService.loginPatient(patient);
		if (loggedInPatient == null) {
			// clear session attributes
			session.setAttribute("professional_id", null);
			session.setAttribute("patient_id", null);
			return ResponseEntity.status(401).body(null);
		} else {
			// set patient session attribute
			session.setAttribute("professional_id", null);
			session.setAttribute("patient_id", loggedInPatient.getId());
			return ResponseEntity.status(200).body(loggedInPatient);
		}
	}
	
	@PostMapping("/professional/login")
	public ResponseEntity<Professional> professionalLogin(@RequestBody LoginDTO professional, HttpSession session) {
		Professional loggedInProfessional = loginService.loginProfessional(professional);
		if (loggedInProfessional == null) {
			// clear session attributes
			session.setAttribute("patient_id", null);
			session.setAttribute("professional_id", null);
			return ResponseEntity.status(401).body(null);
		} else {
			// set professional session attribute
			session.setAttribute("professional_id", loggedInProfessional.getId());
			session.setAttribute("patient_id", null);
			return ResponseEntity.status(200).body(loggedInProfessional);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO user, HttpSession session) {
		
		LoginDTO loggedInUser = loginService.loginUser(user);
		
		if (loggedInUser == null) {
			// clear session attributes
			session.setAttribute("patient_id", null);
			session.setAttribute("professional_id", null);
			return ResponseEntity.status(401).body(null);
			
		} else if (loggedInUser.getPatient() == null) {
			// log professional in
			session.setAttribute("patient_id", null);
			session.setAttribute("professional_id", loggedInUser.getProfessional().getId());
			return ResponseEntity.status(200).body(loggedInUser);
			
		} else {
			// log patient in
			session.setAttribute("patient_id", loggedInUser.getPatient().getId());
			session.setAttribute("professional_id", null);
			return ResponseEntity.status(200).body(loggedInUser);
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Boolean> logout(HttpSession session) {
		
		session.setAttribute("professional_id", null);
		session.setAttribute("patient_id", null);
		
		//System.out.println("Logged user out");
		return ResponseEntity.status(200).body(true);
	}
	
	@GetMapping("/usernames-and-emails")
	public ResponseEntity<List<List<String>>> getUsernamesAndEmails() {
		
		List<List<String>> usernamesAndEmails = loginService.listUsernamesEmails();
		
		if (usernamesAndEmails == null) {
			return ResponseEntity.status(400).body(null);
		} else {
			return ResponseEntity.status(200).body(usernamesAndEmails);
		}
	}
}
