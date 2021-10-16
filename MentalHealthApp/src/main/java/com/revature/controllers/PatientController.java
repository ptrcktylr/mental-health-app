package com.revature.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.EntryRepository;
import com.revature.daos.PatientRepository;
import com.revature.daos.ReplyRepository;
import com.revature.models.Entry;
import com.revature.models.Patient;
import com.revature.models.Reply;

@RestController
@RequestMapping(value="/patient")
public class PatientController {
	
	// patient dao
	private PatientRepository patientDao;
	
	// entry dao
	private EntryRepository entryDao;
	
	// reply dao
	private ReplyRepository replyDao;
	
	// constructor injecting daos
	@Autowired
	public PatientController(PatientRepository patientDao,
							 EntryRepository entryDao,
							 ReplyRepository replyDao
							 ) {
		
		this.patientDao = patientDao;
		
		this.entryDao = entryDao;
		
		this.replyDao = replyDao;
	}
	
	
	// register new user
	@PostMapping("/register")
	public ResponseEntity<Patient> registerPatient(@RequestBody Patient p) {
		
		try {
			patientDao.save(p);
		} catch (Exception exception) {
			System.out.println("Failed to register new patient");
			exception.printStackTrace();
			return ResponseEntity.status(400).body(null);
		}
		
		return ResponseEntity.status(201).body(p);
	}
	
	// add new entry
	@PostMapping("/entry")
	public ResponseEntity<Entry> addEntry(@RequestBody Entry e, HttpSession session) {
		
		try {
			// set logged in patient to entry owner
			Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
			e.setPatient(patientDao.getById(loggedInPatientId));
			
			// set current date to entry date posted
			Date currentDate = new Date();
			e.setDatePosted(currentDate);
			
			entryDao.save(e);
			
		} catch (Exception exception) {
			System.out.println("Failed to add new entry");
			exception.printStackTrace();
			return ResponseEntity.status(400).body(null);
		}
		
		return ResponseEntity.status(201).body(e);
	}
	
	// get single entry
	@GetMapping("/entry/{entry_id}")
	public ResponseEntity<Entry> getEntry(@PathVariable int entry_id, HttpSession session) {
		
		try {
			// get the entry
			Entry e = entryDao.findById(entry_id).get();
			
			// get the current patient's id
			Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
			
			// if user doesn't own this entry
			if (loggedInPatientId == null || e.getPatient().getId() != loggedInPatientId) {
				// if not public return null
				if (!e.isPublic()) {
					System.out.println("Entry with id " + entry_id + " is private!");
					return ResponseEntity.status(401).body(null);
				}
			}
			
			// if the current user is the owner or entry is public, return entry
			return ResponseEntity.status(200).body(e);
			
		} catch (Exception exception) {
			System.out.println("Failed to get entry with id: " + entry_id);
			exception.printStackTrace();
			return ResponseEntity.status(404).body(null);
		}
		
	}
	
	// get all my entries
	@GetMapping("/entries")
	public ResponseEntity<List<Entry>> getPatientsEntries(HttpSession session) {
		
		// get current patient's id
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		
		// if the patient is not logged in return null
		if (loggedInPatientId == null) {
			System.out.println("Patient not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		// return all this patient's entries
		return ResponseEntity.status(200).body(entryDao.findByPatientId(loggedInPatientId));
	}
	
	// get all users' public entries
	@GetMapping("/public/entries")
	public ResponseEntity<List<Entry>> getPublicEntries(HttpSession session) {
		
		// get current patient's id
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		
		// if the patient is not logged in return null
		if (loggedInPatientId == null) {
			System.out.println("Patient not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		// return all this patient's entries
		return ResponseEntity.status(200).body(entryDao.findByIsPublic(true));
	}
	
	// add reply to an entry
	@PostMapping("/entry/{entry_id}/reply")
	public ResponseEntity<?> addReply(@RequestBody Reply r, @PathVariable int entry_id, HttpSession session) {
		
		// get current patient's id
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		if (loggedInPatientId == null) {
			System.out.println("Patient not logged in!");
			return ResponseEntity.status(401).build();
		}
		
		// get entry to add reply to
		try {
			Entry e = entryDao.findById(entry_id).get();
			
			if (!e.isPublic() && e.getPatient() != patientDao.getById(loggedInPatientId)) {
				System.out.println("Patient with id: " + loggedInPatientId + " can't reply to a private entry they didn't create");
				return ResponseEntity.status(401).build();
			}
			
			// set this reply to this entry
			r.setEntry(e);
			
			// set current date to reply date posted
			Date currentDate = new Date();
			r.setDatePosted(currentDate);
			
			// set current patient to reply author
			r.setPatient(patientDao.getById(loggedInPatientId));
			// set professional author to null for safety (replies can't have two authors)
			r.setprofessional(null);
			
			// save the reply
			replyDao.save(r);
			
			return ResponseEntity.status(201).build();
		} catch (Exception exception) {
			// if the entry doesn't exist return null
			System.out.println("No entry with id: " + entry_id);
			return ResponseEntity.status(404).build();
			
		}
		
	}
	
	// delete user entry 
	@DeleteMapping("entry/{entry_id}")
	public ResponseEntity<?> deleteEntry(@PathVariable int entry_id, HttpSession session) {
		
		// get current patient's id
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		
		// if patient is not logged in, return null
		if (loggedInPatientId == null) {
			System.out.println("Patient not logged in!");
			return ResponseEntity.status(401).build();
		}
		
		try {
			Entry e = entryDao.getById(entry_id);
			
			// if this entry doesn't belong to the currently logged in patient
			if (e.getPatient() != patientDao.getById(loggedInPatientId)) {
				// return null
				System.out.println("Entry with id: " + entry_id + " doesn't belong to current patient");
				return ResponseEntity.status(401).build();
			} else {
				// else delete the entry
				entryDao.deleteById(entry_id);
				return ResponseEntity.status(200).build();
			}
			
		} catch (Exception exception) {
			// if the entry doesn't exist return null
			System.out.println("Entry with id: " + entry_id + " doesn't exist");
			return ResponseEntity.status(404).build();
		}
		
	}
}
