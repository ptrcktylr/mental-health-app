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
	
}
