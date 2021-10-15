package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.daos.EntryDao;
import com.revature.daos.ReplyDao;
import com.revature.models.Entry;
import com.revature.models.Reply;
import com.revature.models.User;

@Service
public class PatientService {
	
	EntryDao EDao = new EntryDao();
	ReplyDao RDao = new ReplyDao();
	
	// add entry
	public Entry addEntry(Entry entry, User author) {
		return EDao.insertEntry(entry, author);
	}
	
	// get my entry by id
	public Entry getPrivateEntry(int entryId, User author) {
		return EDao.patientFindEntryById(entryId, author);
	}
	
	// get public entry by id
	public Entry getPublicEntry(int entryId) {
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
	public Reply addReply(Reply reply, Entry entry, User author) {
		return RDao.insertReply(reply, entry, author);
	}
	
}
