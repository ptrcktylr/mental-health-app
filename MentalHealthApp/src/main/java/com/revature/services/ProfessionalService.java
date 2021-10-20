package com.revature.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.EntryRepository;
import com.revature.daos.PatientRepository;
import com.revature.daos.ProfessionalRepository;
import com.revature.daos.ReplyRepository;
import com.revature.models.Entry;
import com.revature.models.Patient;
import com.revature.models.Professional;
import com.revature.models.Reply;

@Service
public class ProfessionalService {
	
	private PatientRepository patientRepository;
	private ProfessionalRepository professionalRepository;
	private ReplyRepository replyRepository;
	private EntryRepository entryRepository;
	
	private ServiceLibrary sl;
	
	@Autowired
	public ProfessionalService(PatientRepository patientRepository,
							   ProfessionalRepository professionalRepository,
							   ReplyRepository replyRepository,
							   EntryRepository entryRepository,
							   ServiceLibrary sl
			) {
		
		this.patientRepository = patientRepository;
		this.professionalRepository = professionalRepository;
		this.replyRepository = replyRepository;
		this.entryRepository = entryRepository;
		this.sl = sl;
		
	}
	
	// register professional
	public Professional registerProfessional(Professional professional) {
		try {
			// check if patients have the same username/email
			if (patientRepository.findPatientWithExistingEmail(professional.getEmail()) != null 
			 || patientRepository.findPatientWithExistingUsername(professional.getUsername()) != null) 
			{
				System.out.println("A patient with the same username or email exists already!");
				return null;
			}
			
			return professionalRepository.save(professional);
		} catch (Exception exception) {
			System.out.println("Failed to register new professional");
			return null;
		}
	}

	// get all patients
	public List<Patient> getAllPatients(int professionalId) {
		try {
			// if professional not logged in
			if (!sl.isProfessionalLoggedIn(professionalId)) {
				//System.out.println("Professional not logged in");
				return null;
			}
			
			// get all patients
			return patientRepository.findAll();
		} catch (Exception e) {
			System.out.println("Could not get all patients for professional "
					+ "with id: " + professionalId);
			return null;
		}
	}
	
	// get all this patients entries (must be assigned to the patient)
	public List<Entry> getAllThisPatientsEntries(int patientId, int professionalId) {
		try {
			// if professional not logged in
			if (!sl.isProfessionalLoggedIn(professionalId)) {
				//System.out.println("Professional not logged in");
				return null;
			}
			
			// if patient isn't assigned to this professional
			Patient patient = patientRepository.findPatientById(patientId);
			if (!sl.isPatientAssignedToProfessional(professionalId, patient.getProfessional().getId(), patientId)) {
				//System.out.println("Professional with id: " + professionalId 
				//		+ " is not assigned to patient with id: " + patientId);
				return null;
			}
			
			// else return the entries
			return entryRepository.findByPatientId(patientId);
			
		} catch (Exception exception) {
			System.out.println("Could not get entries for patient with id: " + patientId);
			return null;
		}
	}
	
	// get all unassigned patients
	public List<Patient> getUnassignedPatients(int professionalId) {
		try {
			// if professional not logged in
			if (!sl.isProfessionalLoggedIn(professionalId)) {
				//System.out.println("Professional not logged in");
				return null;
			}
			// else return unassigned patients
			return patientRepository.findUnassignedPatients();
			
		} catch (Exception exception) {
			System.out.println("Could not get all unassigned patients");
			return null;
		}
	}
	
	// add this patient to my assigned patients
	public Boolean addAssignedPatient(int patientId, int professionalId) {
		try {
			// if professional not logged in
			if (!sl.isProfessionalLoggedIn(professionalId)) {
				//System.out.println("Professional not logged in");
				return false;
			}
			
			Patient patient = patientRepository.findPatientById(patientId);
			
			// if patient has an assigned professional already
			if (sl.doesPatientHaveProfessional(patient.getProfessional(), patientId)) {
				//System.out.println("Patient with id: " + patientId + " already has "
				//						+ "an assigned professional");
				return false;
			}
				
			// else assign
			patient.setProfessional(professionalRepository.findById(professionalId).get());
			patientRepository.save(patient);
			return true;
			
		} catch (Exception exception) {
			System.out.println("Could not assign patient with id: " + patientId 
							+ " to professional with id: " + professionalId);
			return false;
		}
	}
	
	// remove this patient from my assigned patients
	public Boolean removeAssignedPatient(int patientId, int professionalId) {
		try {
			// if professional not logged in
			if (!sl.isProfessionalLoggedIn(professionalId)) {
				//System.out.println("Professional not logged in");
				return false;
			}
			
			Patient patient = patientRepository.findPatientById(patientId);
			
			// if patient isn't assigned to this professional
			if (!sl.doesPatientHaveProfessional(patient.getProfessional(), patientId) || 
					!sl.isPatientAssignedToProfessional(patient.getProfessional().getId(), professionalId, patientId)) {
				//System.out.println("Patient with id: " + patientId + " is not assigned "
				//				+ "to professional with id: " + professionalId);
				return false;
			}
			
			// else remove
			patient.setProfessional(null);
			patientRepository.save(patient);
			return true;
			
		} catch (Exception exception) {
			System.out.println("Could not remove patient with id: " + patientId 
					+ " from professional with id: " + professionalId);
			return false;
		}
	}
	
	// get all this professional's assigned patients
	public List<Patient> getAssignedPatients(int professionalId) {
		try {
			// if professional not logged in
			if (!sl.isProfessionalLoggedIn(professionalId)) {
				//System.out.println("Professional not logged in");
				return null;
			}
			
			Professional professional = professionalRepository.findById(professionalId).get();
			return patientRepository.findPatientsByProfessional(professional);
			
			// get assigned patients
		} catch (Exception exeption) {
			System.out.println("Could not get assigned patients from professional "
								+ "with id: " + professionalId);
			return null;
		}
	}
	
	// get all users' public entries
	public List<Entry> getAllPublicEntries(int professionalId) {
		try {
			// if professional not logged in
			if (!sl.isProfessionalLoggedIn(professionalId)) {
				//System.out.println("Professional not logged in");
				return null;
			}
			
			// else return all public entries
			return entryRepository.findByIsPublic(true);
			
		} catch (Exception exception) {
			System.out.println("Could not get all public entries as professional "
					+ "with id: " + professionalId);
			return null;
		}
	}
	
	// get specific entry from a patient 
	public Entry getEntryFromPatient(int entryId, int professionalId) {
		try {
			// if professional not logged in
			if (professionalId == 0) {
				System.out.println("Professional not logged in");
				return null;
			}
			
			Entry entry = entryRepository.findById(entryId).get();
			
			// if entry is private and belongs to a patient 
			// this professional isn't assigned it
			if (!sl.isEntryPublic(entry) && !sl.isPatientAssignedToProfessional(entry.getPatient()
										  .getProfessional()
										  .getId(), professionalId, entry.getPatient().getId())) {
				//System.out.println("Professional with id: " + professionalId + " is not "
				//		      + "assigned to entry with id: " + entryId + "'s patient");
				return null;
			}
			
			// else return the entry
			return entry;
			
		} catch (Exception exception) {
			System.out.println("Could not get entry with id: " + entryId);
			return null;
		}
	}
	
	// add reply on an entry
	public Reply addReply(Reply reply, int entryId, int professionalId) {
		try {
			// if professional not logged in
			if (!sl.isProfessionalLoggedIn(professionalId)) {
				//System.out.println("Professional not logged in");
				return null;
			}

			Entry entry = entryRepository.findById(entryId).get();

			// if entry is private and entry doesn't belong to professional's assigned patients
			if (!sl.isEntryPublic(entry) && !sl.isPatientAssignedToProfessional(entry.getPatient()
										  .getProfessional()
										  .getId(), professionalId, entry.getPatient().getId())) {
				//System.out.println("Professional with id: " + professionalId + 
				//		" can't reply to private " + "entry with id: " + entryId + 
				//		" because they are not assigned to patient");
				return null;
			}
			// set entry to reply
			reply.setEntry(entry);
			// set professional to reply
			reply.setprofessional(professionalRepository.findById(professionalId).get());
			// set post date
			Date currentDate = new Date();
			reply.setDatePosted(currentDate);
			// return reply
			return replyRepository.save(reply);
			
		} catch (Exception exception) {
			System.out.println("Could not add reply to entry with id: " + entryId + 
					" as professional with id: " + professionalId);
			exception.printStackTrace();
			return null;
		}
	}
	
	// delete reply on an entry (must be assigned to the patient)
	public Boolean deleteReply(int replyId, int professionalId) {
		try {
			// if professional not logged in
			if (sl.isProfessionalLoggedIn(professionalId)) {
				//System.out.println("Professional not logged in");
				return false;
			}
			
			Reply reply = replyRepository.findById(replyId).get();
			
			// if entry is private and professional isn't assigned to the entry's patient
			// return false
			if (!sl.isEntryPublic(reply.getEntry()) && !sl.isPatientAssignedToProfessional(reply.getEntry()
													 .getPatient()
													 .getProfessional()
													 .getId(), professionalId, reply.getEntry().getPatient().getId())) {
				//System.out.println("Professional with id: " + professionalId + 
				//		" can't delete reply with id: " + replyId + 
				//		" because they are not assigned to the patient");
				return false;
			}
			
			// else we can delete reply
			replyRepository.deleteById(replyId);
			return true;
			
		} catch (Exception exception) {
			System.out.println("Can't delete reply with id: " + replyId 
					+ " as professional with id: " + professionalId);
			return false;
		}
	}

	public Professional getProfessional(int professionalId) {
		
		try {
			
			if (!sl.isProfessionalLoggedIn(professionalId)) {
				return null;
			}
			
			Professional professional = professionalRepository.findById(professionalId).get();
			
			return professional;
			
		} catch (Exception exception) {
			System.out.println("Failed to get professional with id: " + professionalId);
			return null;
		}
	}
	
	
}
