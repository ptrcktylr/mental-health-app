package com.revature.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer>{
	
	public List<Entry> findByPatientId(Integer patientId);
	
	public List<Entry> findByIsPublic(Boolean isPublic);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Entry E WHERE E.id = ?1")
	public void deleteById(int entryId);
}
