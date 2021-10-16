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
import com.revature.daos.ProfessionalRepository;
import com.revature.daos.ReplyRepository;
import com.revature.models.Entry;
import com.revature.models.Patient;
import com.revature.models.Professional;
import com.revature.models.Reply;

@RestController
@RequestMapping(value="/professional")
public class ProfessionalController {
	
	// patient dao
	private PatientRepository patientDao;
	
	// professional dao
	private ProfessionalRepository professionalDao;
	
	// reply dao
	private ReplyRepository replyDao;
	
	// entry dao
	private EntryRepository entryDao;
	
	@Autowired
	public ProfessionalController(ProfessionalRepository professionalDao, 
							 	  ReplyRepository replyDao,
							 	  PatientRepository patientDao,
							 	  EntryRepository entryDao
							 	 ) {
		
		this.patientDao = patientDao;
		
		this.professionalDao = professionalDao;
		
		this.replyDao = replyDao;
		
		this.entryDao = entryDao;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<Professional> registerProfessional(@RequestBody Professional p) {
		
		try {
			professionalDao.save(p);
		} catch (Exception exception) {
			System.out.println("Failed to register new professional. Another professional with this username or email exists.");
			exception.printStackTrace();
			return ResponseEntity.status(400).body(null);
		}
		
		return ResponseEntity.status(201).body(p);
		
	}
	
	// professional/patients (GET) gets all patients
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getAllPatients(HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
		
		// if the professional is not logged in return null
		if (loggedInProfessionalId == null) {
			System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.status(200).body(patientDao.findAll());
	}
    
	// professional/patient/{patient_id}/entries (GET) get all this patients entries
	@GetMapping("/patient/{patient_id}/entries")
	public ResponseEntity<List<Entry>> getAllPatientsEntries(@PathVariable int patient_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (loggedInProfessionalId == null) {
			System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		Patient patient = patientDao.getById(patient_id);
		// if patient doesn't exist return null
		if (patient == null) {
			System.out.println("Patient with id: " + patient_id + " doesn't exist");
			return ResponseEntity.status(404).body(null);
		}
		
		// if patient isn't assigned to this professional, return null
		if (patient.getProfessional() != professionalDao.getById(loggedInProfessionalId)) {
			System.out.println("Patient with id: " + patient_id + " isn't assigned to professional");
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.status(200).body(entryDao.findByPatientId(patient_id));
		
	}
	        
	// professional/patient/{patient_id}/add (POST) adds patient to professional's assigned patients
	@PostMapping("/patient/{patient_id}/add")
	public ResponseEntity<?> addMyPatient(@PathVariable int patient_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (loggedInProfessionalId == null) {
			System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		Patient patient = patientDao.getById(patient_id);
		// if patient doesn't exist return null
		if (patient == null) {
			System.out.println("Patient with id: " + patient_id + " doesn't exist");
			return ResponseEntity.status(404).body(null);
		}
		
		// if patient has a professional return null
		if (patient.getProfessional() != null) {
			System.out.println("Patient with id: " + patient_id + " has a professional already");
			return ResponseEntity.status(409).body(null);
		}
		
		// set the logged in professional to the user's assigned professional
		patient.setProfessional(professionalDao.getById(loggedInProfessionalId));
		
		// persist change
		patientDao.save(patient);
		return ResponseEntity.status(200).body(patient);
	}
	      
	// professional/patient/{patient_id}/remove (POST) removes patient from professional's assigned patients
	@PostMapping("/patient/{patient_id}/remove")
	public ResponseEntity<?> removeMyPatient(@PathVariable int patient_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (loggedInProfessionalId == null) {
			System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		Patient patient = patientDao.getById(patient_id);
		// if patient doesn't exist return null
		if (patient == null) {
			System.out.println("Patient with id: " + patient_id + " doesn't exist");
			return ResponseEntity.status(404).body(null);
		}
		
		// if patient is not this professional's, return null
		if (patient.getProfessional() != professionalDao.getById(loggedInProfessionalId)) {
			System.out.println("Patient with id: " + patient_id + " is not professional's patient");
			return ResponseEntity.status(401).body(null);
		}
		
		// set the null to the user's assigned professional
		patient.setProfessional(null);
		
		// persist change
		patientDao.save(patient);
		return ResponseEntity.status(200).body(patient);
	}
	
	// professional/patients/assigned (GET) all this professionals assigned patients
	@GetMapping("/patients/assigned")
	public ResponseEntity<List<Patient>> myAssignedPatients(HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (loggedInProfessionalId == null) {
			System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.status(200).body(patientDao.findPatientsByProfessional(professionalDao.getById(loggedInProfessionalId)));
		
	}
	
	// professional/entries (GET) gets all the public entries
	@GetMapping("/public/entries")
	public ResponseEntity<List<Entry>> getPublicEntries(HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (loggedInProfessionalId == null) {
			System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		// return all public entries
		return ResponseEntity.status(200).body(entryDao.findByIsPublic(true));
	}
	
	// professional/entry/{entry_id} (GET) gets the entry (if the patient is assigned to this professional or if it's public)
	@GetMapping("/entry/{entry_id}")
	public ResponseEntity<Entry> getEntry(@PathVariable int entry_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (loggedInProfessionalId == null) {
			System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		
		try {
			Entry e = entryDao.findById(entry_id).get();
			
			// if entry is public, return it
			if (e.isPublic()) {
				return ResponseEntity.status(200).body(e);
			}
			
			// if entry is private and the professional isn't assigned to this entry's patient return null
			if (e.getPatient().getProfessional() != professionalDao.getById(loggedInProfessionalId)) {
				System.out.println("Entry's patient is not assigned to this professional");
				return ResponseEntity.status(401).body(null);
			}
			
			return ResponseEntity.status(200).body(e);
		} catch (Exception exception) {
			// if the entry doesn't exist return null
			System.out.println("No entry with id: " + entry_id);
			return ResponseEntity.status(404).build();
		}
		
		
		
	}
	        
	// professional/entry/{entry_id}/reply (POST) adds a reply on the entry with this id (if its public or this patient is assigned to them)
	@PostMapping("/entry/{entry_id}/reply")
	public ResponseEntity<?> addReply(@RequestBody Reply r, @PathVariable int entry_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (loggedInProfessionalId == null) {
			System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		// if this entry doesn't exist, return null
		try {
			Entry e = entryDao.findById(entry_id).get();
			
			// if this entry is public, reply to it
			if (e.isPublic()) {
				try {
					// set this reply to this entry
					r.setEntry(e);
					// set current date to reply
					Date currentDate = new Date();
					r.setDatePosted(currentDate);
					// set current professional to reply author
					r.setprofessional(professionalDao.getById(loggedInProfessionalId));
					// set patient author to null for safety (replies can't have two authors)
					r.setPatient(null);
					// save this reply
					replyDao.save(r);
					return ResponseEntity.status(201).build();
				} catch (Exception exception) {
					System.out.println("Invalid input on professional's reply");
					return ResponseEntity.status(422).build();
				}
			}
			
			
			// if this entry is private and the logged in professional is not assigned, return null
			if (e.getPatient().getProfessional() != professionalDao.getById(loggedInProfessionalId)) {
				System.out.println("Entry's patient is not assigned to this professional");
				return ResponseEntity.status(401).body(null);
			}
			
			// reply to this entry (try catch in case of invalid input)
			try {
				// set this reply to this entry
				r.setEntry(e);
				// set current date to reply
				Date currentDate = new Date();
				r.setDatePosted(currentDate);
				// set current professional to reply author
				r.setprofessional(professionalDao.getById(loggedInProfessionalId));
				// set patient author to null for safety (replies can't have two authors)
				r.setPatient(null);
				// save this reply
				replyDao.save(r);
				return ResponseEntity.status(201).build();
			} catch (Exception exception) {
				System.out.println("Invalid input on professional's reply");
				return ResponseEntity.status(422).build();
			}
			
		} catch (Exception exception) {
			System.out.println("Entry with id: " + entry_id + " doesn't exist");
			return ResponseEntity.status(404).build();
		}
		
	}
	
	// professional/entry/{entry_id}/reply/{reply_id} (DELETE) deletes a reply on the entry with this id (if its public or this patient is assigned to them)
	@DeleteMapping("/reply/{reply_id}")
	public ResponseEntity<?> deleteReply(@PathVariable int reply_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (loggedInProfessionalId == null) {
			System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		
		try {
			Reply r = replyDao.getById(reply_id);
			
			// if this reply's entry is public we can delete
			if (r.getEntry().isPublic()) {
				replyDao.deleteById(reply_id);
				return ResponseEntity.status(200).build();
			} else if (r.getEntry().getPatient().getProfessional() != professionalDao.getById(loggedInProfessionalId)) {
				// if this reply's entry is private 
				// and the professional isn't assigned to the entry's patient
				// return null
				System.out.println("The patient of the entry this reply belongs to is not assigned to the professional");
				return ResponseEntity.status(401).build();
			} else {
				// delete the reply
				replyDao.deleteById(reply_id);
				return ResponseEntity.status(200).build();
			}
			
		} catch (Exception exception) {
			// if the reply doesn't exist return null
			System.out.println("Reply with id: " + reply_id + " doesn't exist");
			return ResponseEntity.status(404).build();
		}
		
	}
	
	
}
