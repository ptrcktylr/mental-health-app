package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Entry;
import com.revature.models.Patient;
import com.revature.models.Professional;
import com.revature.models.Reply;
import com.revature.services.ProfessionalService;

@RestController
@RequestMapping(value="/professional")
@CrossOrigin(origins="http://localhost:4200/", allowCredentials="true")
public class ProfessionalController {
	
	// professional service
	private ProfessionalService professionalService;
	
	// controller 'library'
	private ControllerLibrary ch;
	
	@Autowired
	public ProfessionalController(ProfessionalService professionalService,
			ControllerLibrary ch
							 	 ) {
		
		this.professionalService = professionalService;
		this.ch = ch;
	}
	
	
	// register new professional
	@PostMapping("/register")
	public ResponseEntity<Professional> registerProfessional(@RequestBody Professional p) {
		Professional registeredProfessional = professionalService.registerProfessional(p);
		if (registeredProfessional != null) {
			return ResponseEntity.status(201).body(registeredProfessional);
		} else {
			return ResponseEntity.status(409).body(null);
		}
	}
	
	// professional/patients (GET) gets all patients
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getAllPatients(HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
		
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		List<Patient> patients = professionalService.getAllPatients(loggedInProfessionalId);
		return ResponseEntity.status(200).body(patients);
	}
    
	// professional/patient/{patient_id}/entries (GET) get all this patients entries
	@GetMapping("/patient/{patient_id}/entries")
	public ResponseEntity<List<Entry>> getAllPatientsEntries(@PathVariable int patient_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		List<Entry> entries = professionalService
				.getAllThisPatientsEntries(patient_id, loggedInProfessionalId);
		
		if (entries == null) {
			return ResponseEntity.status(401).body(null);
		} else {
			return ResponseEntity.status(200).body(entries);
		}
		
	}
	        
	// professional/patient/{patient_id}/add (POST) adds patient to professional's assigned patients
	@PostMapping("/patient/{patient_id}/add")
	public ResponseEntity<Boolean> addMyPatient(@PathVariable int patient_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(false);
		}
		
		if (!professionalService.addAssignedPatient(patient_id, loggedInProfessionalId)) {
			return ResponseEntity.status(401).body(false);
		} else {
			return ResponseEntity.status(200).body(true);
		}
	}
	      
	// professional/patient/{patient_id}/remove (POST) removes patient from professional's assigned patients
	@PostMapping("/patient/{patient_id}/remove")
	public ResponseEntity<Boolean> removeMyPatient(@PathVariable int patient_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(false);
		}
		
		if (!professionalService.removeAssignedPatient(patient_id, loggedInProfessionalId)) {
			return ResponseEntity.status(401).body(false);
		} else {
			return ResponseEntity.status(200).body(true);
		}
		
	}
	
	// professional/patients/assigned (GET) all this professionals assigned patients
	@GetMapping("/patients/assigned")
	public ResponseEntity<List<Patient>> myAssignedPatients(HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		List<Patient> patients = professionalService.getAssignedPatients(loggedInProfessionalId);
		
		if (patients == null) {
			return ResponseEntity.status(401).body(null);
		} else {
			return ResponseEntity.status(200).body(patients);
		}
	}
	
	// professional/entries (GET) gets all the public entries
	@GetMapping("/public/entries")
	public ResponseEntity<List<Entry>> getPublicEntries(HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		// return all public entries
		List<Entry> entries = professionalService.getAllPublicEntries(loggedInProfessionalId);
		if (entries == null) {
			return ResponseEntity.status(401).body(null);
		} else {
			return ResponseEntity.status(200).body(entries);
		}
	}
	
	// professional/entry/{entry_id} (GET) gets the entry (if the patient is assigned to this professional or if it's public)
	@GetMapping("/entry/{entry_id}")
	public ResponseEntity<Entry> getEntry(@PathVariable int entry_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		Entry entry = professionalService.getEntryFromPatient(entry_id, loggedInProfessionalId);
		
		if (entry == null) {
			return ResponseEntity.status(401).body(null);
		} else {
			return ResponseEntity.status(200).body(entry);
		}
	}
	        
	// professional/entry/{entry_id}/reply (POST) adds a reply on the entry with this id (if its public or this patient is assigned to them)
	@PostMapping("/entry/{entry_id}/reply")
	public ResponseEntity<Reply> addReply(@RequestBody Reply r, @PathVariable int entry_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		Reply reply = professionalService.addReply(r, entry_id, loggedInProfessionalId);
		if (reply == null) {
			return ResponseEntity.status(401).body(null);
		} else {
			return ResponseEntity.status(201).body(reply);
		}
		
	}
	
	// professional/entry/{entry_id}/reply/{reply_id} (DELETE) deletes a reply on the entry with this id (if its public or this patient is assigned to them)
	@DeleteMapping("/reply/{reply_id}")
	public ResponseEntity<Boolean> deleteReply(@PathVariable int reply_id, HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		
		Boolean deleteReplySuccessful = professionalService.deleteReply(reply_id, loggedInProfessionalId);
		if (!deleteReplySuccessful) {
			return ResponseEntity.status(401).body(false);
		} else {
			return ResponseEntity.status(200).body(true);
		}
		
	}
	
	@GetMapping("/patients/unassigned")
	public ResponseEntity<List<Patient>> getAllUnassignedPatients(HttpSession session) {
		
		// get current professional's id
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
				
		// if the professional is not logged in return null
		if (ch.isIdNull(loggedInProfessionalId)) {
			//System.out.println("Professional not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		List<Patient> patients = professionalService.getUnassignedPatients(loggedInProfessionalId);
		
		if (patients == null) {
			return ResponseEntity.status(400).body(null);
		} else {
			return ResponseEntity.status(200).body(patients);
		}
	}
	
	@GetMapping("my-info")
	public ResponseEntity<Professional> getMyInfo(HttpSession session) {
		
		Integer loggedInProfessionalId = (Integer) session.getAttribute("professional_id");
		
		if (ch.isIdNull(loggedInProfessionalId)) {
			return ResponseEntity.status(401).body(null);
		}
		
		Professional professional = professionalService.getProfessional(loggedInProfessionalId);
		
		if (professional == null) {
			return ResponseEntity.status(401).body(null);
		} else {
			return ResponseEntity.status(200).body(professional);
		}
	}
	
	
}
