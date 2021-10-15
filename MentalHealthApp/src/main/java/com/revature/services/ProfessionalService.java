package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.daos.EntryDao;
import com.revature.daos.ReplyDao;
import com.revature.daos.UserDao;
import com.revature.models.Entry;
import com.revature.models.Reply;
import com.revature.models.User;

@Service
public class ProfessionalService {
	
	EntryDao EDao = new EntryDao();
	UserDao UDao = new UserDao();
	ReplyDao RDao = new ReplyDao();

	// get all patients
	public List<User> getAllPatients() {
		return UDao.findAllPatients();
	}
	
	// //get this patients entries
	public List<Entry> getPatientsEntries(User author, User professional) {
		return EDao.professionalFindAllEntriesByAuthor(author, professional);
	}
	
	// add this patient to assigned patients
	public boolean addPatient(User professional, User patient) {
		return UDao.addPatient(professional, patient);
	}
	
	// remove this patient from assigned patients
	public boolean removePatient(User professional, User patient) {
		return UDao.removePatient(professional, patient);
	}
	
	// get this patients private entry
	public Entry getPatientsEntry(int entryId, User professional) {
		return EDao.professionalFindEntryById(entryId, professional);
	}
	
	// get this patients public entry
	public Entry getPublicEntry(int entryId) {
		return EDao.findEntryById(entryId);
	}
	
	// get all public entries
	public List<Entry> getAllEntries() {
		return EDao.findAllPublicEntries();
	}
	
	// add reply to this entry
	public Reply addReply(Reply reply, Entry entry, User user) {
		return RDao.insertReply(reply, entry, user);
	}
	
}
