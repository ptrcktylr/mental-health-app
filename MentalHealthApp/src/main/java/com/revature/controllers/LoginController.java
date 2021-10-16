package com.revature.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.PatientRepository;
import com.revature.daos.ProfessionalRepository;
import com.revature.models.LoginDTO;
import com.revature.models.Patient;
import com.revature.models.Professional;

@RestController
@CrossOrigin
public class LoginController {
	
	private PatientRepository patientDao;
	private ProfessionalRepository professionalDao;
	
	@Autowired
	public LoginController(PatientRepository patientDao, ProfessionalRepository professionalDao) {
		super();
		this.patientDao = patientDao;
		this.professionalDao = professionalDao;
	}
	
	@PostMapping("/patient/login")
	public ResponseEntity<?> patientLogin(@RequestBody LoginDTO patient, HttpSession session) {
		
		Patient loggedInPatient = patientDao.validLogin(patient.getUsername(), patient.getPassword());
		
		// fail to log patient in
		if (loggedInPatient == null) {
			System.out.println("Failed to log patient in!");
			
			//clear session ids
			session.setAttribute("professional_id", null);
			session.setAttribute("patient_id", null);
			return ResponseEntity.status(401).build();
		}
		
		// log patient
		session.setAttribute("patient_id", loggedInPatient.getId());
		session.setAttribute("professional_id", null);
		
		System.out.println("Session's patient id is: "+ session.getAttribute("patient_id"));
		System.out.println("Session's professional id is: "+ session.getAttribute("professional_id"));
		return ResponseEntity.status(200).body(loggedInPatient);
	}
	
	@PostMapping("/professional/login")
	public ResponseEntity<?> professionalLogin(@RequestBody LoginDTO professional, HttpSession session) {
		
		Professional loggedInProfessional = professionalDao.validLogin(professional.getUsername(), professional.getPassword());
		
		// fail to log professional in
		if (loggedInProfessional == null) {
			System.out.println("Failed to log professional in!");
			
			//clear session ids
			session.setAttribute("professional_id", null);
			session.setAttribute("patient_id", null);
			return ResponseEntity.status(401).build();
		}
		
		// log professional
		session.setAttribute("professional_id", loggedInProfessional.getId());
		session.setAttribute("patient_id", null);
		
		System.out.println("Session's professional id is: "+ session.getAttribute("professional_id"));
		System.out.println("Session's patient id is: "+ session.getAttribute("patient_id"));
		return ResponseEntity.status(200).body(loggedInProfessional);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		
		session.setAttribute("professional_id", null);
		session.setAttribute("patient_id", null);
		
		System.out.println("Logged user out");
		return ResponseEntity.status(200).build();
	}
}
