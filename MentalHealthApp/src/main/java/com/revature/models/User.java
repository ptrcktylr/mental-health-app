package com.revature.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username", nullable=false, unique=true)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="is_professional", nullable=false)
	private boolean isProfessional;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="patients_professionals",
	 joinColumns=@JoinColumn(name="professional_id"),
	 inverseJoinColumns=@JoinColumn(name="patient_id")
	)
	private Set<User> assignedPatients;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="patients_professionals",
	 joinColumns=@JoinColumn(name="patient_id"),
	 inverseJoinColumns=@JoinColumn(name="professional_id")
	)
	private Set<User> assignedProfessionals;
	
	// no args constructor
	public User() {
		super();
	}

	public User(int id, String username, String password, boolean isProfessional, String firstName, String lastName,
			String email, Set<User> assignedPatients, Set<User> assignedProfessionals) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isProfessional = isProfessional;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.assignedPatients = assignedPatients;
		this.assignedProfessionals = assignedProfessionals;
	}

	public User(String username, String password, boolean isProfessional, String firstName, String lastName,
			String email) {
		super();
		this.username = username;
		this.password = password;
		this.isProfessional = isProfessional;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
		this.assignedPatients = new HashSet<User>();
		this.assignedProfessionals = new HashSet<User>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isProfessional() {
		return isProfessional;
	}

	public void setProfessional(boolean isProfessional) {
		this.isProfessional = isProfessional;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<User> getAssignedPatients() {
		return assignedPatients;
	}

	public void setAssignedPatients(Set<User> assignedPatients) {
		this.assignedPatients = assignedPatients;
	}
	
	public void addAssignedPatient(User assignedPatient) {
		this.assignedPatients.add(assignedPatient);
	}
	
	public void removeAssignedPatient(User assignedPatient) {
		this.assignedPatients.remove(assignedPatient);
	}

	public Set<User> getAssignedProfessionals() {
		return assignedProfessionals;
	}

	public void setAssignedProfessionals(Set<User> assignedProfessionals) {
		this.assignedProfessionals = assignedProfessionals;
	}
	
	public void addAssignedProfessional(User assignedProfessional) {
		this.assignedProfessionals.add(assignedProfessional);
	}
	
	public void removeAssignedProfessional(User assignedProfessional) {
		this.assignedProfessionals.remove(assignedProfessional);
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + (isProfessional ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (isProfessional != other.isProfessional)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", isProfessional="
				+ isProfessional + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
}
