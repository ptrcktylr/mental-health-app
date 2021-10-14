package com.revature.daos;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDao {

	public List<User> findAllUsers() {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		// use HQL to get all users referring to the User Class instead of the users table
		@SuppressWarnings("unchecked")
		List<User> userList = ses.createQuery("FROM User").list();
		
		// close Session
		HibernateUtil.closeSession();
		
		return userList;
	}
	
	public List<User> findAllPatients() {
		
		Session ses = HibernateUtil.getSession();
		String HQL = "FROM User U WHERE U.isProfessional = false";
		
		@SuppressWarnings("unchecked")
		List<User> userList = ses.createQuery(HQL).list();
		
		return userList;
		
	}
	
	public List<User> findAllProfessionals() {
		
		Session ses = HibernateUtil.getSession();
		String HQL = "FROM User U WHERE U.isProfessional = true";
		
		@SuppressWarnings("unchecked")
		List<User> userList = ses.createQuery(HQL).list();
		
		return userList;
		
	}
	
	public List<User> findAllAssignedPatients(int professionalId) {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		// create HQL
		String HQL = "SELECT U.assignedPatients FROM User U WHERE U.id = :professional_id AND U.isProfessional = true";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("professional_id", professionalId);
		
		try {
			List<User> patients = query.getResultList();
			if (patients.isEmpty()) { return null; }
			return patients;
		} catch (NoResultException nre) {
			System.out.println("No patients for professional with id of " + professionalId);
		}
		
		return null;
	}
	
	public List<User> findAllAssignedPatients(User professional) {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		// if this is a professional return null
		if (!professional.isProfessional()) {
			System.out.println("User with id of " + professional.getId() + " is a patient");
			return null;
		}
		
		// create HQL
		String HQL = "SELECT U.assignedPatients FROM User U WHERE U.id = :professional_id";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("professional_id", professional.getId());
		
		try {
			List<User> patients = query.getResultList();
			return patients;
		} catch (NoResultException nre) {
			System.out.println("No patients for professional with id of " + professional.getId());
		}
		
		return null;
	}
	
	public List<User> findAllAssignedProfessionals(int patientId) {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		// create HQL
		String HQL = "SELECT U.assignedProfessionals FROM User U WHERE U.id = :patient_id AND U.isProfessional = false";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("patient_id", patientId);
		
		try {
			List<User> professionals = query.getResultList();
			if (professionals.isEmpty()) { return null; }
			return professionals;
		} catch (NoResultException nre) {
			System.out.println("No professionals for patient with id of " + patientId);
		}
		
		return null;
		
	}
	
	public List<User> findAllAssignedProfessionals(User patient) {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		// if this is a professional return null
		if (patient.isProfessional()) {
			System.out.println("User with id of " + patient.getId() + " is a professional");
			return null;
		}
		
		// create HQL
		String HQL = "SELECT U.assignedProfessionals FROM User U WHERE U.id = :patient_id";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("patient_id", patient.getId());
		
		try {
			List<User> professionals = query.getResultList();
			return professionals;
		} catch (NoResultException nre) {
			System.out.println("No professionals for patient with id of " + patient.getId());
		}
		
		return null;
		
	}

	public User validLogin(String username, String password) {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		// create HQL
		String HQL = "FROM User U WHERE U.username = :username AND U.password = :password";
		
		// create Query from HQL and set parameters
		Query query = ses.createQuery(HQL);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		// return user if username & password current
		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (NoResultException nre) {
			System.out.println("Failed to log in!"); // TODO, log this instead of printing
		}
		
		// if username & password incorrect
		return null;
	}
	
	public User insertUser(User user) {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		// save user to database
		ses.save(user);
		
		// close Session
		HibernateUtil.closeSession();
		
		return user;
	}
	
	public void addPatient(User professional, User patient) {
		
		Session ses = HibernateUtil.getSession();
		ses.beginTransaction();
		
		// add patient to professional's set 
		professional.addAssignedPatient(patient);
		
		// add professional to patient's set
		patient.addAssignedProfessional(professional);
		
		ses.saveOrUpdate(professional);
		
		ses.getTransaction().commit();
		
		// close Session
		HibernateUtil.closeSession();
		
	}
	
	public User findUserById(int id) {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		// query using Hibernate session and User class
		User user = ses.get(User.class, id);
		
		// close Session
		HibernateUtil.closeSession();
		
		return user;
		
	}
	
	public void updateUser(User user) {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		//update and delete must happen within a transaction
		Transaction tran = ses.beginTransaction();
		
		// assign query to a string
		String HQL = "Update User SET username = '" + user.getUsername() 
		+ "', password = '" + user.getPassword() 
		+ "', is_professional = '" + user.isProfessional() 
		+ "', first_name = '" + user.getFirstName() 
		+ "', last_name = '" + user.getLastName() 
		+ "', email = '" + user.getEmail() 
		+ "' WHERE id = " + user.getId();
		
		Query q = ses.createQuery(HQL);
		
		q.executeUpdate();
		
		tran.commit();
		HibernateUtil.closeSession();
	}
	
	
}
