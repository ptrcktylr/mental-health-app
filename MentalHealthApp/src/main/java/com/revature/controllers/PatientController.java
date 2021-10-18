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
import com.revature.models.Reply;
import com.revature.services.PatientService;

@RestController
@RequestMapping(value="/patient")
@CrossOrigin(origins="http://localhost:4200/", allowCredentials="true")
public class PatientController {
	
	// patient service
	private PatientService patientService;
	
	// controller 'library'
	private ControllerLibrary ch;
	
	
	// constructor injecting service
	@Autowired
	public PatientController(
							 PatientService patientService,
							 ControllerLibrary ch
							 ) {
		this.patientService = patientService;
		this.ch = ch;
	}
	
	
	// register new patient
	@PostMapping("/register")
	public ResponseEntity<Patient> registerPatient(@RequestBody Patient p) {
		Patient registeredPatient = patientService.registerPatient(p);
		if (registeredPatient != null) {
			return ResponseEntity.status(201).body(registeredPatient);
		} else {
			return ResponseEntity.status(409).body(null);
		}
	}
	
	// add new entry
	@PostMapping("/entry")
	public ResponseEntity<Entry> addEntry(@RequestBody Entry e, HttpSession session) {
		
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		
		// if patient not logged in
		if (ch.isIdNull(loggedInPatientId)) {
			return ResponseEntity.status(401).body(null);
		}
		
		Entry addedEntry = patientService.addEntry(e, loggedInPatientId);
		if (addedEntry == null) {
			return ResponseEntity.status(409).body(null);
		} else {
			return ResponseEntity.status(201).body(addedEntry);
		}
	}
	
	// get single entry
	@GetMapping("/entry/{entry_id}")
	public ResponseEntity<Entry> getEntry(@PathVariable int entry_id, HttpSession session) {
		
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		
		// if patient not logged in
		if (ch.isIdNull(loggedInPatientId)) {
			return ResponseEntity.status(401).body(null);
		}
		
		Entry entry = patientService.getEntry(entry_id, loggedInPatientId);
		if (entry == null) {
			return ResponseEntity.status(401).body(null);
		} else {
			return ResponseEntity.status(200).body(entry);
		}
		
	}
	
	// get all my entries
	@GetMapping("/entries")
	public ResponseEntity<List<Entry>> getPatientsEntries(HttpSession session) {
		
		// get current patient's id
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		
		// if the patient is not logged in return null
		//if (loggedInPatientId == null) {
		if (ch.isIdNull(loggedInPatientId)) {
			//System.out.println("Patient not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		// get entries
		List<Entry> entries = patientService.getMyEntries(loggedInPatientId);
		
		// return all this patient's entries
		return ResponseEntity.status(200).body(entries);
	}
	
	// get all patients' public entries
	@GetMapping("/public/entries")
	public ResponseEntity<List<Entry>> getPublicEntries(HttpSession session) {
		
		// get current patient's id
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		
		// if the patient is not logged in return null
		if (ch.isIdNull(loggedInPatientId)) {
			//System.out.println("Patient not logged in!");
			return ResponseEntity.status(401).body(null);
		}
		
		// return all public entries
		List<Entry> entries = patientService.getAllPublicEntries(loggedInPatientId);
		return ResponseEntity.status(200).body(entries);
	}
	
	// add reply to an entry
	@PostMapping("/entry/{entry_id}/reply")
	public ResponseEntity<Reply> addReply(@RequestBody Reply r, @PathVariable int entry_id, HttpSession session) {
		
		// get current patient's id
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		if (ch.isIdNull(loggedInPatientId)) {
			//System.out.println("Patient not logged in!");
			return ResponseEntity.status(401).build();
		}
		
		Reply addedReply = patientService.addReply(r, entry_id, loggedInPatientId);
		
		if (addedReply == null) {
			return ResponseEntity.status(400).body(null);
		} else {
			return ResponseEntity.status(201).body(addedReply);
		}
		
	}
	
	// delete patient entry 
	@DeleteMapping("entry/{entry_id}")
	public ResponseEntity<Boolean> deleteEntry(@PathVariable int entry_id, HttpSession session) {
		
		// get current patient's id
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		
		// if patient is not logged in, return null
		if (ch.isIdNull(loggedInPatientId)) {
			//System.out.println("Patient not logged in!");
			return ResponseEntity.status(401).body(false);
		}
		
		Boolean deletedSuccessfully = patientService.deleteEntry(entry_id, loggedInPatientId);
		
		if (deletedSuccessfully) {
			return ResponseEntity.status(200).body(deletedSuccessfully);
		} else {
			return ResponseEntity.status(400).body(deletedSuccessfully);
		}
		
	}
	
	// delete patient reply
	@DeleteMapping("reply/{reply_id}")
	public ResponseEntity<Boolean> deleteReply(@PathVariable int reply_id, HttpSession session) {
		
		// get current patient's id
		Integer loggedInPatientId = (Integer) session.getAttribute("patient_id");
		
		// if patient is not logged in, return null
		if (ch.isIdNull(loggedInPatientId)) {
			//System.out.println("Patient not logged in!");
			return ResponseEntity.status(401).body(false);
		}
		
		Boolean deletedSuccessfully = patientService.deleteReply(reply_id, loggedInPatientId);
		
		if (deletedSuccessfully) {
			return ResponseEntity.status(200).body(deletedSuccessfully);
		} else {
			return ResponseEntity.status(400).body(deletedSuccessfully);
		}
	}
}
