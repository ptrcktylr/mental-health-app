package com.revature.aspects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
}
