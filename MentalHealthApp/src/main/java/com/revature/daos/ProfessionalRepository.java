package com.revature.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Integer>{

	@Query("FROM Professional P WHERE P.username=?1 AND P.password=?2")
	public Professional validLogin(String username, String password);
	
}
