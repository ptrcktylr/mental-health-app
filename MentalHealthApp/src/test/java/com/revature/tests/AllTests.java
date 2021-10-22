package com.revature.tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.Entry;
import com.revature.models.LoginDTO;
import com.revature.models.Patient;
import com.revature.models.Professional;
import com.revature.models.Reply;
import com.revature.services.LoginService;
import com.revature.services.PatientService;
import com.revature.services.ProfessionalService;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AllTests {
	
	private PatientService patientService;
	private ProfessionalService professionalService;	
	private LoginService loginService;
	
	
	@Autowired
	public AllTests(PatientService patientService, ProfessionalService professionalService,
			LoginService loginService) {
		super();
		this.patientService = patientService;
		this.professionalService = professionalService;
		this.loginService = loginService;
	}
	

	@Test
	@DisplayName("test of registering patient ")
	@Order(1)
	public void testRegisterPatient() { 
		
		Patient patient1 = patientService.registerPatient(new Patient("Tom", "p@ssw0rd", "Tom", "Lee", "tom@example.com"));
		Patient patient2 = patientService.registerPatient(new Patient("Lucy", "p@ssw0rd", "Lucy", "Smith", "lucy@example.com"));
		
		assertEquals(1, patient1.getId());
		assertEquals("Tom", patient1.getUsername());
		assertEquals("p@ssw0rd", patient1.getPassword());
		assertEquals("Tom", patient1.getFirstName());
		assertEquals("Lee", patient1.getLastName());
		assertEquals("tom@example.com", patient1.getEmail());
		
		assertEquals(2, patient2.getId());
		assertEquals("Lucy", patient2.getUsername());
		assertEquals("p@ssw0rd", patient2.getPassword());
		assertEquals("Lucy", patient2.getFirstName());
		assertEquals("Smith", patient2.getLastName());
		assertEquals("lucy@example.com", patient2.getEmail());
	}
	
	
	@Test
	@DisplayName("test of logging patient in")
	@Order(2)
	public void testLoginPatient() { 
		
		Patient patient = loginService.loginPatient(new LoginDTO("Tom", "p@ssw0rd"));
		
		assertEquals("Tom", patient.getUsername());
		assertEquals("p@ssw0rd", patient.getPassword());

	}
	
	@Test
	@DisplayName("test of getting patient")
	@Order(3)
	public void testGetPatient() {
		
		int patientId = 1;
		Patient patient = patientService.getPatient(patientId);
		
		assertEquals("Tom", patient.getUsername());
		assertEquals("p@ssw0rd", patient.getPassword());
		assertEquals("Tom", patient.getFirstName());
		assertEquals("Lee", patient.getLastName());
		assertEquals("tom@example.com", patient.getEmail());
	}
	
	
	
	@Test
	@DisplayName("test of registering professional")
	@Order(4)
	public void testRegisterProfessional() { 
		
		Professional professional = professionalService.registerProfessional(new Professional("Jane", "p@ssw0rd", "Jane", "Lee", "jane@example.com"));
			
		assertEquals(1, professional.getId());
		assertEquals("Jane", professional.getUsername());
		assertEquals("p@ssw0rd", professional.getPassword());
		assertEquals("Jane", professional.getFirstName());
		assertEquals("Lee", professional.getLastName());
		assertEquals("jane@example.com", professional.getEmail());
	}
	
	

	@Test
	@DisplayName("test of logging professional in")
	@Order(5)
	public void testLoginProfessional() { 
		
		Professional professional = loginService.loginProfessional(new LoginDTO("Jane", "p@ssw0rd"));
		
		assertEquals("Jane", professional.getUsername());
		assertEquals("p@ssw0rd", professional.getPassword());

	}
	

	@Test
	@DisplayName("test of posting a new patient entry")
	@Order(6)
	public void testAddEntry() {
			
		Entry entry1 = new Entry("diary1", "I am sad yesterday", new Date(), false, "negative", -1, null, null);
		Entry entry2 = new Entry("diary2", "I am happy today", new Date(), true, "positive", 1, null, null);
		int patientId = 1;
		
		patientService.addEntry(entry1, patientId);
		patientService.addEntry(entry2, patientId);
		
		Entry addedEntry1 = patientService.addEntry(entry1, patientId);
		Entry addedEntry2 = patientService.addEntry(entry2, patientId);
		
		assertEquals(1, addedEntry1.getId());
		assertEquals("diary1", addedEntry1.getTitle());
		assertEquals("I am sad yesterday", addedEntry1.getBody());
		assertEquals(false, addedEntry1.isPublic());
		assertEquals("negative", addedEntry1.getTag());
		assertEquals(-1, addedEntry1.getSentiment());
		assertEquals(1, addedEntry1.getPatient().getId());
		
		assertEquals(2, addedEntry2.getId());	
		assertEquals("diary2", addedEntry2.getTitle());
		assertEquals("I am happy today", addedEntry2.getBody());
		assertEquals(true, addedEntry2.isPublic());
		assertEquals("positive", addedEntry2.getTag());
		assertEquals(1, addedEntry2.getSentiment());
		assertEquals(1, addedEntry2.getPatient().getId());
	}
	
	
	@Test
	@DisplayName("test of getting all entries of a patient")
	@Order(7)
	public void testGetMyEntries() {
		
		int patientId = 1;
		
		int count = patientService.getMyEntries(patientId).size();
				
		assertEquals(2, count);
	}
	
	
	
	@Test
	@DisplayName("test of getting a single entry of a patient")
	@Order(8)
	public void testGetEntry() {
		
		 try {
			    int entryId = 1;
				int patientId = 1;
				Entry entry = patientService.getEntry(entryId, patientId);
				
				assertEquals("diary1", entry.getTitle());
				assertEquals("I am sad yesterday", entry.getBody());
				assertEquals(false, entry.isPublic());
				assertEquals("negative", entry.getTag());
				assertEquals(-1, entry.getSentiment());	
		    } catch (Throwable expected) {
		    	
		        assertEquals(NullPointerException.class, expected.getClass());
		    }				
	}
	

	
	@Test
	@DisplayName("test of getting all publi entries of a patient")
	@Order(9)
	public void testGetPatientAllPublicEntries() {
		
		int patientId = 1;
		
		int count = patientService.getAllPublicEntries(patientId).size();
		
		assertEquals(1, count);
		
	}
	
	
	
	@Test
	@DisplayName("test of adding a patient to a professional's assigned patients")
	@Order(10)
	public void testAddAssignedPatient() {
		
		int patientId = 1; 
		int professionalId = 1;
		
		assertTrue(professionalService.addAssignedPatient(patientId, professionalId));			
	}
	
	
	
	@Test
	@DisplayName("test of getting assigned professional of the patient")
	@Order(11)
	public void testGetAssignedProfessional() {
		
		int patientId = 1;
		System.out.println(patientService.getAssignedProfessional(patientId));
		Professional professional = patientService.getAssignedProfessional(patientId);
		assertEquals("Jane Lee", professional.getFirstName() + " " + professional.getLastName());
	}
	
	
	
	
	@Test
	@DisplayName("test of adding reply to entry with id entry_id (if entry is public or entry belongs to logged in patient)")
	@Order(12)
	public void testAddPatientReply(){
		
		int entryId = 1;
		int patientId = 1;
			
		Reply reply = new Reply("Reply from patient1", new Date(), null, null, null);
		Reply addedReply = patientService.addReply(reply, entryId, patientId);
		
		assertEquals("Reply from patient1", addedReply.getBody());				
	}
	
	
	
	@Test
	@DisplayName("test for adding reply to this entry (if private entry, the author [patient] must be assigned to the logged in professional")
	@Order(13)
	public void testAddProfessionalReply() {
				
		Reply reply = new Reply("Reply from profeesional1", new Date(), null, null, null);
		int entryId = 1;
		int professionalId = 1;
		
		Reply addedReply = professionalService.addReply(reply, entryId, professionalId);
			
		assertEquals("Reply from profeesional1", addedReply.getBody());
		assertEquals(1, addedReply.getEntry().getId());
		assertEquals(1, addedReply.getprofessional().getId());
		//assertEquals(1, addedReply.getPatient().getId());	
	}
	
	
	
	@Test
	@DisplayName("test for getting all patients who are not assigned a professional")
	@Order(14)
	public void testGetUnassignedPatients() {
		
		int professionalId = 1;
		
		assertEquals(1, professionalService.getUnassignedPatients(professionalId).size());
		assertEquals(2, professionalService.getUnassignedPatients(professionalId).get(0).getId());		
	}
	
	
	
	@Test
	@DisplayName("test for getting all entries from patient with id patient_id (if logged in professional is assigned to patient)")
	@Order(15)
	public void testGetAllThisPatientsEntries() {
		
		int patientId = 1;
		int professionalId = 1;
		
		assertEquals(2, professionalService.getAllThisPatientsEntries(patientId, professionalId).size());
	}
	
	
	
	@Test
	@DisplayName("test for getting all public entries from a professional")
	@Order(16)
	public void testGetProfessionalAllPublicEntries() {
		
		int professionalId = 1;
		
		assertEquals(1, professionalService.getAllPublicEntries(professionalId).size());
		assertEquals(2, professionalService.getAllPublicEntries(professionalId).get(0).getId());
	}

	
	
	@Test
	@DisplayName("test for getting entry with id entry_id (if entry is private, logged in professional must be assigned to patient")
	@Order(17)
	public void testGetEntryFromPatient() {
		
		int entryId = 1;
		int professionalId = 1;
		Entry entry = professionalService.getEntryFromPatient(entryId, professionalId);	
		
		assertEquals(1, entry.getPatient().getId());
		assertEquals("diary1", entry.getTitle());
		assertEquals("I am sad yesterday", entry.getBody());
		assertEquals(false, entry.isPublic());
		assertEquals("negative", entry.getTag());
		assertEquals(-1, entry.getSentiment());		
	}
	
	
	
	@Test
	@DisplayName("test for deleting patient reply with id reply_id (if reply belongs to logged in patient)")
	@Order(18)
	public void testDeleteReply() {
		
		int replyId = 1;
		int patientId = 1;
		
		assertTrue(patientService.deleteReply(replyId, patientId));		
	}

	
	
	@Test
	@DisplayName("test for deleting patient entry with id entry_id (if entry belongs to logged in patient)")
	@Order(19)
	public void testDeleteEntry() {
		
		int entryId = 2;
		int patientId = 1;
		
		assertTrue(patientService.deleteEntry(entryId, patientId));			
	}

	
	
	@Test
	@DisplayName("test for removing a patient from logged in professional (patient must be assigned to logged in professional)")
	@Order(20)
	public void testRemoveAssignedPatient() {
		
		int patientId = 1;
		int professionalId = 1;
		
		assertTrue(professionalService.removeAssignedPatient(patientId, professionalId));		
	}	
}
