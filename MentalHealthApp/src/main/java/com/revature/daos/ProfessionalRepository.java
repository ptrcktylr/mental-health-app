package com.revature.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Integer>{

	@Query("FROM Professional P WHERE P.username=?1 AND P.password=?2")
	public Professional validLogin(String username, String password);
	
	@Query("SELECT P.username FROM Professional P")
	public List<String> getAllUsernames();
	
	@Query("SELECT P.email FROM Professional P")
	public List<String> getAllEmails();
	
}
