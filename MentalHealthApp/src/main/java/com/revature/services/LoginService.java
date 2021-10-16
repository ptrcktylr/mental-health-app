package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.PatientRepository;
import com.revature.daos.ProfessionalRepository;
import com.revature.models.LoginDTO;
import com.revature.models.Patient;
import com.revature.models.Professional;

@Service
public class LoginService {
	
	private PatientRepository patientRepository;
	private ProfessionalRepository professionalRepository;
	
	@Autowired
	public LoginService(PatientRepository patientRepository,
						ProfessionalRepository professionalRepository
						) {
		this.patientRepository = patientRepository;
		this.professionalRepository = professionalRepository;
	}
	
	// log in patient
	public Patient loginPatient(LoginDTO patientLoginDTO) {
		
		try {
			// get patient by credentials
			Patient loggedInPatient = patientRepository.validLogin(
											patientLoginDTO.getUsername(), 
											patientLoginDTO.getPassword()
									  );
			
			// if credentials incorrect
			if (loggedInPatient == null) {
				System.out.println("Failed to log patient with username: " 
									+ patientLoginDTO.getUsername() + " in");
				return null;
			}
			
			// if correct, return user so controller can set session attributes
			return loggedInPatient;
		} catch (Exception exception) {
			System.out.println("Input login information missmatch");
			return null;
		}
		
	}
	
	// log in professional
	public Professional loginProfessional(LoginDTO professionalLoginDTO) {
		
		try {
			// get patient by credentials
			Professional loggedInProfessional = professionalRepository.validLogin(
													professionalLoginDTO.getUsername(), 
													professionalLoginDTO.getPassword()
												);
			
			// if credentials incorrect
			if (loggedInProfessional == null) {
				System.out.println("Failed to log professional with username: " 
									+ professionalLoginDTO.getUsername() + " in");
				return null;
			}
			
			// if correct, return user so controller can set session attributes
			return loggedInProfessional;
		} catch (Exception exception) {
			System.out.println("Input login information missmatch");
			return null;
		}
		
	}
	
}
