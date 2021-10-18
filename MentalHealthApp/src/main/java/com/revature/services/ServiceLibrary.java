package com.revature.services;

import org.springframework.stereotype.Component;

import com.revature.models.Entry;
import com.revature.models.Patient;
import com.revature.models.Professional;


// another class that is used for improved logs
@Component
public class ServiceLibrary {
	
	public boolean isPatientNull(Patient p, String username) {
		return p == null;
	}
	
	public boolean isProfessionalNull(Professional p, String username) {
		return p == null;
	}
	
	public boolean isEntryPublic(Entry entry) {
		return entry.isPublic();
	}
	
	public boolean isEntryOwnedByPatient(int entry, int id) {
		return entry == id;
	}
	
	public boolean isEntryPublicOwnedByPatient(Entry entry, int id) {
		return isEntryPublic(entry) && isEntryOwnedByPatient(entry.getPatient().getId(), id);
	}
	
	public boolean isPatientLoggedIn(int id) {
		return id != 0;
	}
	
	public boolean isReplyOwnedByPatient(int reply, int id) {
		return reply == id;
	}
	
	public boolean isProfessionalLoggedIn(int id) {
		return id != 0;
	}
	
	public boolean isPatientAssignedToProfessional(int proid, int proid2, int patid) {
		return proid == proid2;
	}
	
	public boolean doesPatientHaveProfessional(Professional p, int patid) {
		return p != null;
	}
}
