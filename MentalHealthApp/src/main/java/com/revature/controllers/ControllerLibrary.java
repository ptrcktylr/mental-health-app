package com.revature.controllers;

import org.springframework.stereotype.Component;

// For now made for logging
// If I find a better way to log methods being called within other methods, I'll get rid of this

@Component
public class ControllerLibrary {

	// ----------------------------------- Login Controller --------------------------------------
	// check if the patient id is null
	public boolean isIdNull(Integer id) {
		return id == null;
	}
}