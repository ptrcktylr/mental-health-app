package com.revature.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Reply;
import com.revature.models.User;
import com.revature.services.EntryService;
import com.revature.services.ProfessionalService;
import com.revature.services.UserService;


@RestController
@CrossOrigin
public class ProfessionalController {
	
	@Autowired
	ProfessionalService professionalService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EntryService entryService;
	
	public User loginedUser(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Object loginedUsername = session.getAttribute("loginedUser");
		
		return userService.findUserByUsername(loginedUsername.toString());
		
	}
	
	@PostMapping("/patient/{patient_id}/add")
	public Map<String, Object> addPatient(@RequestBody User patientUser,
			             @PathVariable("patient_id") Integer patient_id,
			             HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", professionalService.addPatient(loginedUser(request), userService.findUserById(patient_id)));
		map.put("status_code", 200);
		
		return map;
	}
	
	
	@PostMapping("/professional/entry/{entry_id}/reply")
	public Map<String, Object> addReply(@RequestBody Reply reply,
			             @PathVariable("entry_id") Integer entry_id,
			             HttpServletRequest request) {

		Map<String, Object> map = new HashMap<>();
		map.put("result", professionalService.addReply(reply, entryService.findEntryById(entry_id), loginedUser(request)));		
		map.put("status_code", 200);
		
		return map;	
	}
	
	
	@ResponseBody
	@GetMapping("professional/patients")
	public Map<String, Object> getAllPatients() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", professionalService.getAllPatients());
		map.put("status_code", 200);
		
		return map;	
	}
	
	
	@ResponseBody
	@GetMapping("/professional/patient/{patient_id}/entries")
	public Map<String, Object> getIndividualEntries(@PathVariable("patient_id") Integer patient_id,
			                                        HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", professionalService.getPatientsEntries(userService.findUserById(patient_id), loginedUser(request)));
		map.put("status_code", 200);
		
		return map;	
	}
	

	@ResponseBody
	@GetMapping("/professional/entry/{entry_id")
	public Map<String, Object> getEntry(@PathVariable("entry_id") Integer entry_id,
			              HttpServletRequest request) {

		Map<String, Object> map = new HashMap<>();
		map.put("result", professionalService.getPatientsEntry(entry_id, loginedUser(request)));
		map.put("status_code", 200);
		
		return map;
	}
	
	
	@ResponseBody
	@GetMapping("professional/entries")
	public Map<String, Object> getAllPublicEntries() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", professionalService.getAllEntries());
		map.put("status_code", 200);
		
		return map;		
	}
	
	
	@GetMapping("professional/patient/{patient_id}/remove")
	public Map<String, Object> removePatient(@PathVariable("patient_id") Integer patient_id,
			                                 HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", professionalService.removePatient(loginedUser(request), userService.findUserById(patient_id)));
		map.put("status_code", 200);
		
		return map;		
	}	
}
