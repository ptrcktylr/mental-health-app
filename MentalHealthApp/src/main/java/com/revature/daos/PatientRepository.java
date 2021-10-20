package com.revature.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Patient;
import com.revature.models.Professional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	@Query("FROM Patient P WHERE P.username=?1 AND P.password=?2")
	public Patient validLogin(String username, String password);
	
	@Query("FROM Patient P WHERE P.professional=?1")
	public List<Patient> findPatientsByProfessional(Professional professional);
	
	@Query("FROM Patient P WHERE P.id=?1")
	public Patient findPatientById(int patientId);
	
	@Query("FROM Patient P WHERE P.professional=null")
	public List<Patient> findUnassignedPatients();
	
	@Query("SELECT P.username FROM Patient P")
	public List<String> getAllUsernames();
	
	@Query("SELECT P.email FROM Patient P")
	public List<String> getAllEmails();
	
	@Query("FROM Patient P WHERE P.username=?1")
	public Patient findPatientWithExistingUsername(String username);
	
	@Query("FROM Patient P WHERE P.email=?1")
	public Patient findPatientWithExistingEmail(String email);
}
