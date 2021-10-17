package com.revature.services;

import java.util.ArrayList;
import java.util.List;

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
				System.out.println("Failed to login patient with username: " 
									+ patientLoginDTO.getUsername());
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
				System.out.println("Failed to login professional with username: " 
									+ professionalLoginDTO.getUsername());
				return null;
			}
			
			// if correct, return user so controller can set session attributes
			return loggedInProfessional;
		} catch (Exception exception) {
			System.out.println("Input login information missmatch");
			return null;
		}
		
	}
	
	// get all existing usernames and emails
	public List<List<String>> listUsernamesEmails() {
		try {
			
			// get all the requires lists
			List<String> patientUsernames = patientRepository.getAllUsernames();
			List<String> patientEmails = patientRepository.getAllEmails();
			List<String> professionalUsernames = professionalRepository.getAllUsernames();
			List<String> professionalEmails = professionalRepository.getAllEmails();
			
			// create two lists to combine each type
			List<String> usernames = new ArrayList<>(patientUsernames.size() + professionalUsernames.size());
			List<String> emails = new ArrayList<>(patientEmails.size() + professionalEmails.size());
			
			// create large list to return all info
			List<List<String>> usernamesAndEmails = new ArrayList<>(2); // 2d list [[usernames], [emails]]
			
			// add to lists
			usernames.addAll(patientUsernames);
			usernames.addAll(professionalUsernames);
			emails.addAll(patientEmails);
			emails.addAll(professionalEmails);
			
			// add to list to be returned
			usernamesAndEmails.add(usernames);
			usernamesAndEmails.add(emails);
			
			// return giant list
			return usernamesAndEmails;
			
		} catch (Exception exception) {
			System.out.println("Could not get all existing usernames and emails");
			return null;
		}
	}
	
}
