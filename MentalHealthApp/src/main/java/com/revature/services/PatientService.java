package com.revature.services;

import java.util.List;

import com.revature.daos.EntryDao;
import com.revature.models.Entry;
import com.revature.models.Reply;
import com.revature.models.User;

public class PatientService {
	
	EntryDao EDao = new EntryDao();
	
	// add entry
	public Entry addEntry(Entry entry, User author) {
		return EDao.insertEntry(entry, author);
	}
	
	// get my entry by id
	public Entry getEntry(int entryId, User author) {
		return EDao.patientFindEntryById(entryId, author);
	}
	
	// get public entry by id
	public Entry getEntry(int entryId) {
		return EDao.findEntryById(entryId);
	}
	
	// get all this patients entries
	public List<Entry> getPatientsEntries(User author) {
		return EDao.patientFindAllEntriesByAuthor(author);
	}
	
	// get all public entries
	public List<Entry> getAllPublicEntries() {
		return EDao.findAllPublicEntries();
	}
	
	// add reply to entry by id
	public Reply addReply() {
		return null;
	}
	
}
