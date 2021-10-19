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
	
	private ServiceLibrary sl;
	
	@Autowired
	public LoginService(PatientRepository patientRepository,
						ProfessionalRepository professionalRepository,
						ServiceLibrary sl
						) {
		this.patientRepository = patientRepository;
		this.professionalRepository = professionalRepository;
		this.sl = sl;
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
			if (sl.isPatientNull(loggedInPatient, patientLoginDTO.getUsername())) {
				//System.out.println("Failed to login patient with username: " 
				//					+ patientLoginDTO.getUsername());
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
			if (sl.isProfessionalNull(loggedInProfessional, professionalLoginDTO.getUsername())) {
				//System.out.println("Failed to login professional with username: " 
				//					+ professionalLoginDTO.getUsername());
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

	public LoginDTO loginUser(LoginDTO userDTO) {
		try {
			
			// log in both patient and professional
			Patient patient = patientRepository.validLogin(userDTO.getUsername(), userDTO.getPassword());
			Professional professional = professionalRepository.validLogin(userDTO.getUsername(), userDTO.getPassword());
			
			// if patient and professional are null, no user with those credentials exist
			if (patient == null && professional == null) {
				System.out.println("No user with username " + userDTO.getUsername() + " & password exists!");
				return null;
			// if patient and professional are both not null, there's two users with the same info
			} else if (patient != null && professional != null) {
				System.out.println("Two users with the same login info exist!");
				return null;
			// if patient is null, set professional on the DTO and return the DTO
			} else if (patient == null) {
				System.out.println("Logged professional in!");
				userDTO.setProfessional(professional);
				userDTO.setAccountType("Professional");
				return userDTO;
			// if professional is null, set patient on the DTO and return the DTO
			} else {
				System.out.println("Logged patient in!");
				userDTO.setPatient(patient);
				userDTO.setAccountType("Patient");
				return userDTO;
			}
			
		} catch (Exception exception) {
			System.out.println("Input login information missmatch!");
			return null;
		}
	}
	
}
