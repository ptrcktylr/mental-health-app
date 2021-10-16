package com.revature.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.EntryRepository;
import com.revature.daos.PatientRepository;
import com.revature.daos.ReplyRepository;
import com.revature.models.Entry;
import com.revature.models.Patient;
import com.revature.models.Reply;

@Service
public class PatientService {
	
	private PatientRepository patientRepository;
	private EntryRepository entryRepository;
	private ReplyRepository replyRepository;
	
	@Autowired
	public PatientService(PatientRepository patientRepository, 
						  EntryRepository entryRepository,
						  ReplyRepository replyRepository
						  ) {
		this.patientRepository = patientRepository;
		this.entryRepository = entryRepository;
		this.replyRepository = replyRepository;
	}
	
	// register new user
	public Patient registerPatient(Patient patient) {
		try {
			return patientRepository.save(patient);
		} catch (Exception exception) {
			System.out.println("Failed to register new patient");
			return null;
		}
	}
	
	// add new entry
	public Entry addEntry(Entry entry, int patientId) {
		try {
			
			// set patient as owner
			entry.setPatient(patientRepository.getById(patientId));
			// set current date as date posted
			Date currentDate = new Date();
			entry.setDatePosted(currentDate);
			
			return entryRepository.save(entry);
			
		} catch (Exception exception) {
			System.out.println("Failed to add new entry");
			return null;
		}
	}
	
	// get single entry
	public Entry getEntry(int entryId, int patientId) {
		try {
			Entry entry = entryRepository.getById(entryId);
			
			// if it's private and if this entry doesn't belong to this patient return null
			if (!entry.isPublic() && entry.getPatient().getId() != patientId) {
				System.out.println("Entry with id: " + entryId + " doesn't belong to patient with id: " + patientId);
				return null;
			} else {
				return entry;
			}
			
		} catch (Exception exception) {
			System.out.println("Failed to get entry with id: " + entryId);
			return null;
		}
	}
	
	// get all my entries
	public List<Entry> getMyEntries(int patientId) {
		try {
			// if patient not logged in return null
			if (patientId == 0) {
				System.out.println("Patient not logged in");
				return null;
			}
			// get all this patients entries
			return entryRepository.findByPatientId(patientId);
			
		} catch (Exception exception) {
			System.out.println("Failed to get entries from patient with id: " + patientId);
			return null;
		}
	}
	
	// get all patients' public entries
	public List<Entry> getAllPublicEntries(int patientId) {
		try {
			// if patient not logged in
			if (patientId == 0) {
				System.out.println("Patient not logged in");
				return null;
			}
			// get all public entries
			return entryRepository.findByIsPublic(true);
		} catch (Exception exception) {
			System.out.println("Failed to get all public entries");
			return null;
		}
	}
	
	// add reply to an entry
	public Reply addReply(Reply reply, int entryId, int patientId) {
		try {
			// if patient not logged in
			if (patientId == 0) {
				System.out.println("Patient not logged in");
				return null;
			}
			
			Entry entry = entryRepository.getById(entryId);
			
			// if entry is private and isn't this patients
			if (!entry.isPublic() && entry.getPatient().getId() != patientId) {
				System.out.println("Patient with id: " + patientId + " can not reply to "
						+ "private entry with id: " + entryId);
				return null;
			}
			
			// set entry to this reply
			reply.setEntry(entry);
			// set patient to this reply
			reply.setPatient(patientRepository.getById(patientId));
			// set date posted
			Date currentDate = new Date();
			reply.setDatePosted(currentDate);
			
			// return reply
			return replyRepository.save(reply);
			
		} catch (Exception exception) {
			System.out.println("Failed to add reply to entry with id: " + entryId 
									+ " as patient with id: " + patientId);
			return null;
		}
	}
	
	// delete patient entry
	public Boolean deleteEntry(int entryId, int patientId) {
		try {
			// if patient not logged in
			if (patientId == 0) {
				System.out.println("Patient not logged in");
				return false;
			}
			
			Entry entry = entryRepository.getById(entryId);
			
			// if entry isn't this patients
			if (entry.getPatient().getId() != patientId) {
				System.out.println("Entry with id: " + entryId + " doesn't belong to "
						+ "patient with id: " + patientId);
				return false;
			}
			
			// delete entry
			entryRepository.deleteById(entryId);
			System.out.println("Entry with id: " + entryId + " deleted successfully");
			return true;
			
		} catch (Exception exception) {
			System.out.println("Failed to delete entry with id: " + entryId 
								+ " as patient with id: " + patientId);
			return false;
		}
	}
	
	// delete patient reply
	public Boolean deleteReply(int replyId, int patientId) {
		try {
			// if patient not logged in
			if (patientId == 0) {
				System.out.println("Patient not logged in");
				return false;
			}
			
			Reply reply = replyRepository.getById(replyId);
			
			// if this reply isn't this patients
			if (reply.getPatient().getId() != patientId) {
				System.out.println("Reply with id: " + replyId + " doesn't belong to "
						+ "patient with id: " + patientId);
				return false;
			}
			
			// delete reply
			replyRepository.deleteById(replyId);
			System.out.println("Reply with id: " + replyId + " deleted successfully");
			return true;
			
		} catch (Exception exception) {
			System.out.println("Failed to delete reply with id: " + replyId 
					+ " as patient with id: " + patientId);
			return false;
		}
	}
	
}
