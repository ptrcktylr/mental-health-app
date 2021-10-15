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

import com.revature.models.Entry;
import com.revature.models.Reply;
import com.revature.models.User;
import com.revature.services.EntryService;
import com.revature.services.PatientService;
import com.revature.services.UserService;


@RestController
@CrossOrigin
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EntryService entryService;
	
	
	public User loginedUser(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Object loginedUsername = session.getAttribute("loginedUser");
		
		return userService.findUserByUsername(loginedUsername.toString());
		
	}
	
	@PostMapping("/patient/entry")
	public Map<String, Object> addEntry(@RequestBody Entry entry, 
			                            HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", patientService.addEntry(entry, loginedUser(request)));
		map.put("status_code", 200);
		
		return map;
	}
	
	
	@PostMapping("/patient/entry/{entry_id}/reply")
	public Map<String, Object> addReply(@RequestBody Reply reply,
			                            @PathVariable("entry_id") Integer entry_id,
			                            HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", patientService.addReply(reply, entryService.findEntryById(entry_id), loginedUser(request)));
		map.put("status_code", 200);
		
		return map;
	}
	
	
	@ResponseBody
	@GetMapping("/patient/entry/{entry_id}")
	public Map<String, Object> getEntry(@PathVariable("entry_id") Integer entry_id,
			                            HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", patientService.getPrivateEntry(entry_id, loginedUser(request)));
		map.put("status_code", 200);
		
		return map;	
	}
	
	
	@ResponseBody
	@GetMapping("/patient/entries")
	public Map<String, Object> getAllEntries(HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", patientService.getPatientsEntries(loginedUser(request)));
		map.put("status_code", 200);
		
		return map;
	}
	
	
	@ResponseBody
	@GetMapping("/patient/public/entries")
	public Map<String, Object> getAllPatientsPublicEntries() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", patientService.getAllPublicEntries());
		map.put("status_code", 200);
		
		return map;
	}

}
