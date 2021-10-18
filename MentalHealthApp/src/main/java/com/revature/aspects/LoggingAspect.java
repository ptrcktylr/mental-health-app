package com.revature.aspects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.revature.models.Entry;
import com.revature.models.Patient;
import com.revature.models.Professional;
import com.revature.models.Reply;

@Component
@Aspect
public class LoggingAspect {
	
	private static Logger log = LogManager.getLogger(LoggingAspect.class);
	
	// ------------------------------ Login Controller ----------------------------------------------
	
	@AfterReturning("execution(* com.revature.controllers.LoginController.logout(..))")
	public void logLoginControllerLogout(JoinPoint jp) {
		log.info(jp.getSignature() + " ----- USER LOGGED OUT");
	}
	
	// methods that return a Patient
	@AfterReturning(pointcut="execution(* com.revature.controllers.LoginController.*(..))", returning="returnedObject")
	public void logLoginControllerPatient(JoinPoint jp, ResponseEntity<Patient> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	// methods that return a Professional
	@AfterReturning(pointcut="execution(* com.revature.controllers.LoginController.*(..))", returning="returnedObject")
	public void logLoginControllerProfessional(JoinPoint jp, ResponseEntity<Professional> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	// methods that return a 2D List of Strings
	@AfterReturning(pointcut="execution(* com.revature.controllers.LoginController.*(..))", returning="returnedObject")
	public void logLoginControllerUsernamesEmails(JoinPoint jp, ResponseEntity<List<List<String>>> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	// ------------------------------ Patient Controller ----------------------------------------------
	
	// methods that return a list of entries
	@AfterReturning(pointcut="execution(* com.revature.controllers.PatientController.*(..))", returning="returnedObject")
	public void logPatientControllerListEntries(JoinPoint jp, ResponseEntity<List<Entry>> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	// methods that return Entries
	@AfterReturning(pointcut="execution(* com.revature.controllers.PatientController.*(..))", returning="returnedObject")
	public void logPatientControllerEntry(JoinPoint jp, ResponseEntity<Entry> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	// methods that return Patients
	@AfterReturning(pointcut="execution(* com.revature.controllers.PatientController.*(..))", returning="returnedObject")
	public void logPatientControllerPatient(JoinPoint jp, ResponseEntity<Patient> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	// methods that return Replies
	@AfterReturning(pointcut="execution(* com.revature.controllers.PatientController.*(..))", returning="returnedObject")
	public void logPatientControllerReply(JoinPoint jp, ResponseEntity<Reply> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	// methods that return Booleans
	@AfterReturning(pointcut="execution(* com.revature.controllers.PatientController.*(..))", returning="returnedObject")
	public void logPatientControllerDelete(JoinPoint jp, ResponseEntity<Boolean> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	// ------------------------------ Professional Controller ------------------------------------------
	
	@AfterReturning(pointcut="execution(* com.revature.controllers.ProfessionalController.*(..))", returning="returnedObject")
	public void logProfessionalControllerProfessional(JoinPoint jp, ResponseEntity<Professional> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	@AfterReturning(pointcut="execution(* com.revature.controllers.ProfessionalController.*(..))", returning="returnedObject")
	public void logProfessionalControllerListPatient(JoinPoint jp, ResponseEntity<List<Patient>> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	@AfterReturning(pointcut="execution(* com.revature.controllers.ProfessionalController.*(..))", returning="returnedObject")
	public void logProfessionalControllerListEntries(JoinPoint jp, ResponseEntity<List<Entry>> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	@AfterReturning(pointcut="execution(* com.revature.controllers.ProfessionalController.*(..))", returning="returnedObject")
	public void logProfessionalControllerBoolean(JoinPoint jp, ResponseEntity<Boolean> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	@AfterReturning(pointcut="execution(* com.revature.controllers.ProfessionalController.*(..))", returning="returnedObject")
	public void logProfessionalControllerEntry(JoinPoint jp, ResponseEntity<Entry> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	@AfterReturning(pointcut="execution(* com.revature.controllers.ProfessionalController.*(..))", returning="returnedObject")
	public void logProfessionalControllerReply(JoinPoint jp, ResponseEntity<Reply> returnedObject) {
		
		if(returnedObject.getBody() != null) {
			
			int status = returnedObject.getStatusCodeValue();
			
			if(status == 200 || status == 201) {
				log.info(jp.getSignature() + " ----- SUCCESSFUL");
				return;
			}
		}
		
		log.info(jp.getSignature() + " ----- FAILED - " + returnedObject.getStatusCode());
	}
	
	// --------------------------------------------- Controller 'Library' ------------------------------------------------------------
	
	@AfterReturning(pointcut="execution(* com.revature.controllers.ControllerLibrary.isIdNull(..))", returning="returnedBool")
	public void logIsPatientIdNull(JoinPoint jp, boolean returnedBool) {

		if(returnedBool == true) {
			log.info(jp.getSignature() + " ----- USER NOT LOGGED IN");
		}
	}

	// --------------------------------------------- Login Service -------------------------------------------------------------------
	
	@AfterReturning(pointcut="execution(* com.revature.services.LoginService.*(..))", returning="returnedObject")
	public void logLoginServicePatient(JoinPoint jp, Patient returnedObject) {
		
		if(returnedObject != null) {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
			return;
		}
		
		log.info(jp.getSignature() + " ----- FAILED");
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.LoginService.*(..))", returning="returnedObject")
	public void logLoginServiceProfessional(JoinPoint jp, Professional returnedObject) {
		
		if(returnedObject != null) {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
			return;
		}
		
		log.info(jp.getSignature() + " ----- FAILED");
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.LoginService.*(..))", returning="returnedObject")
	public void logLoginServiceListString(JoinPoint jp, List<List<String>> returnedObject) {
		if(returnedObject == null) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.LoginService.*(..))", throwing="thrownException")
	public void logLoginServiceException(JoinPoint jp, Exception thrownException) {
		log.info(jp.getSignature() + " threw an exception: " + thrownException.getClass());
	}
	
	// --------------------------------------------- Patient Service --------------------------------------------------------------
	
	@AfterReturning(pointcut="execution(* com.revature.services.PatientService.*(..))", returning="returnedObject") 
	public void logPatientServiceRegister(JoinPoint jp, Patient returnedObject) {
		
		if(returnedObject == null) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.PatientService.*(..))", returning="returnedObject") 
	public void logPatientServiceEntry(JoinPoint jp, Entry returnedObject) {
		
		if(returnedObject == null) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.PatientService.*(..))", returning="returnedObject") 
	public void logPatientServiceListEntry(JoinPoint jp, List<Entry> returnedObject) {
		
		if(returnedObject == null) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.PatientService.deleteEntry(..))", returning="returnedBool") 
	public void logPatientServiceDeleteEntry(JoinPoint jp, Boolean returnedBool) {
		
		if(returnedBool == false) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info("ENTRY WITH ID: " + jp.getArgs()[0] + " SUCESSFULLY DELETED");
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.PatientService.deleteReply(..))", returning="returnedBool") 
	public void logPatientServiceDeleteReply(JoinPoint jp, Boolean returnedBool) {
		
		if(returnedBool == false) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info("REPLY WITH ID: " + jp.getArgs()[0] + " SUCESSFULLY DELETED");
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	// ---------------------------------------------- Professional Service ----------------------------------------------------------
	
	@AfterReturning(pointcut="execution(* com.revature.services.ProfessionalService.*(..))", returning="returnedObject") 
	public void logProfessionalServiceRegister(JoinPoint jp, Professional returnedObject) {
		
		if(returnedObject == null) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ProfessionalService.*(..))", returning="returnedObject") 
	public void logProfessionalServiceListPatient(JoinPoint jp, List<Patient> returnedObject) {
		
		if(returnedObject == null) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ProfessionalService.*(..))", returning="returnedObject") 
	public void logProfessionalServiceListEntry(JoinPoint jp, List<Entry> returnedObject) {
		
		if(returnedObject == null) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ProfessionalService.deleteReply(..))", returning="returnedBool") 
	public void logProfessionalServiceDeleteReply(JoinPoint jp, Boolean returnedBool) {
		if(returnedBool == false) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info("REPLY WITH ID: " + jp.getArgs()[0] + " SUCESSFULLY DELETED");
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ProfessionalService.*(..))", returning="returnedObject") 
	public void logProfessionalServiceEntry(JoinPoint jp, Entry returnedObject) {
		if(returnedObject == null) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ProfessionalService.*(..))", returning="returnedObject") 
	public void logProfessionalServiceReply(JoinPoint jp, Reply returnedObject) {
		
		if(returnedObject == null) {
			log.info(jp.getSignature() + " ----- FAILED");
		}
		else {
			log.info(jp.getSignature() + " ----- SUCCESSFUL");
		}
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.ProfessionalService.*(..))", throwing="returnedException") 
	public void logProfessionalServiceReplyError(JoinPoint jp, Exception returnedException) {
		
		log.info(jp.getSignature() + " ----- FAILED");
	}
	
	// ----------------------------------------- Service 'Library' -----------------------------------------------------------------
	
	@AfterReturning(pointcut="execution(* com.revature.services.ServiceLibrary.isPatientNull(..))", returning="returnedBool")
	public void logIsLoggedInPatientNull(JoinPoint pjp, boolean returnedBool) {
		
		if(returnedBool == true) {
			log.info(pjp.getSignature() + " ----- FAILED TO LOGIN PATIENT WITH USERNAME: " + pjp.getArgs()[1]);
		}
		
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ServiceLibrary.isProfessionalNull(..))", returning="returnedBool")
	public void logIsLoggedInProfessionalNull(JoinPoint pjp, boolean returnedBool) {
		
		if(returnedBool == true) {
			log.info(pjp.getSignature() + " ----- FAILED TO LOGIN PROFESSIONAL WITH USERNAME: " + pjp.getArgs()[1]);
		}
		
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ServiceLibrary.isEntryPublic(..))", returning="returnedBool") 
	public void logIsEntryPublic(JoinPoint pjp, boolean returnedBool) {

		// if the entry is private
		if(!returnedBool) {
			log.info(pjp.getSignature() + " ----- ENTRY IS PRIVATE");
		}
		
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ServiceLibrary.isPatientLoggedIn(..))", returning="returnedBool") 
	public void logIsPatientLoggedIn(JoinPoint jp, boolean returnedBool) {
		
		// if the patient is not logged in
		if(!returnedBool) {
			log.info(jp.getSignature() + " ----- PATIENT NOT LOGGED IN");
		}
	}
	
	@Around("execution(* com.revature.services.ServiceLibrary.isEntryPublicOwnedByPatient(..))") 
	public boolean logIsEntryPublicOwnedByPatient(ProceedingJoinPoint pjp) {

		Entry entry = (Entry)pjp.getArgs()[0];
		// if the ids dont match
		if(entry.getPatient().getId() != (int)pjp.getArgs()[1]) {
			log.info(pjp.getSignature() + " ----- ENTRY WITH ID: " + pjp.getArgs()[0] + " DOESN'T BELONG TO PATIENT WITH ID: " + pjp.getArgs()[1]);
			return false;
		}
		
		return true;
	}
	
	@Around("execution(* com.revature.services.ServiceLibrary.isReplyOwnedByPatient(..))") 
	public boolean logIsReplyOwnedByPatient(ProceedingJoinPoint pjp) {
		
		// if the ids dont match
		if((int)pjp.getArgs()[0] != (int)pjp.getArgs()[1]) {
			log.info(pjp.getSignature() + " ----- REPLY WITH ID: " + pjp.getArgs()[0] + " DOESN'T BELONG TO PATIENT WITH ID: " + pjp.getArgs()[1]);
			
			return false;
		}
		
		return true;
	}

	@AfterReturning(pointcut="execution(* com.revature.services.ServiceLibrary.isProfessionalLoggedIn(..))", returning="returnedBool") 
	public void logIsProfessionalLoggedIn(JoinPoint jp, boolean returnedBool) {
		
		// if the patient is not logged in
		if(!returnedBool) {
			log.info(jp.getSignature() + " ----- PROFESSIONAL NOT LOGGED IN");
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ServiceLibrary.isPatientAssignedToProfessional(..))", returning="returnedBool") 
	public void logIsPatientAssignedToProfessional(JoinPoint jp, boolean returnedBool) {
		// if the patient is not logged in
		if(!returnedBool) {
			log.info(jp.getSignature() + " ----- PROFESSIONAL WITH ID: " + jp.getArgs()[1] + " IS NOT ASSIGNED TO PATIENT WITH ID: " + jp.getArgs()[2]);
		}
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.ServiceLibrary.doesPatientHaveProfessional(..))", returning="returnedBool") 
	public void logDoesPatientHaveProfessional(JoinPoint jp, boolean returnedBool) {
		// if the patient does not have a professional assigned to them
		if(!returnedBool) {
			log.info(jp.getSignature() + " ----- PATIENT WITH ID: " + jp.getArgs()[1] + " DOES NOT HAVE AN ASSIGNED PROFESSIONAL");
		}
	}
}
