package com.revature.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.revature.models.Entry;
import com.revature.models.Patient;
import com.revature.models.Reply;

public interface EntryRepository extends JpaRepository<Entry, Integer>{
	
	public List<Entry> findByPatientId(Integer patientId);
	
	public List<Entry> findByIsPublic(Boolean isPublic);
}
