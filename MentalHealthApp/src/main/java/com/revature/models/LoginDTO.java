package com.revature.models;

public class LoginDTO {
	
	private String username;
	private String password;
	
	private Patient patient;
	private Professional professional;
	
	private String accountType;
	
	public LoginDTO() {
		super();
	}

	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
		this.professional = null;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
		this.patient = null;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "LoginDTO [username=" + username + ", password=" + password + ", patient=" + patient + ", professional="
				+ professional + ", accountType=" + accountType + "]";
	}
	
}
