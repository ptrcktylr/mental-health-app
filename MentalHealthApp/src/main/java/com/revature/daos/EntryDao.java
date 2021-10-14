package com.revature.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;

import com.revature.models.Entry;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class EntryDao {

	// find all entries
	public List<Entry> findAllEntries() {
		
		Session ses = HibernateUtil.getSession();
		
		@SuppressWarnings("unchecked")
		List<Entry> entryList = ses.createQuery("FROM Entry").list();
		
		HibernateUtil.closeSession();
		
		return entryList;
	}
	
	// find all public entries
	public List<Entry> findAllPublicEntries() {
		
		Session ses = HibernateUtil.getSession();
		
		@SuppressWarnings("unchecked")
		List<Entry> entryList = ses.createQuery("FROM Entry E WHERE E.isPublic = true").list();
		
		HibernateUtil.closeSession();
		
		return entryList;
	}
	
	// find all entries (public & private) from author
	public List<Entry> patientFindAllEntriesByAuthor(User author) {
		
		Session ses = HibernateUtil.getSession();
		String HQL = "FROM Entry E WHERE E.author = :author";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("author", author);
		
		try {
			List<Entry> entryList = query.getResultList();
			HibernateUtil.closeSession();
			return entryList;
		} catch (NoResultException nre) {
			System.out.println("No entries for patient with id of " + author.getId());
		}
		
		HibernateUtil.closeSession();
		return null;
		
	}
	
	// find all entries (public & private) from author id
	public List<Entry> patientFindAllEntriesByAuthor(int authorId) {
		
		Session ses = HibernateUtil.getSession();
		String HQL = "FROM Entry E WHERE E.author.id = :author_id";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("author_id", authorId);
		
		try {
			List<Entry> entryList = query.getResultList();
			HibernateUtil.closeSession();
			return entryList;
		} catch (NoResultException nre) {
			System.out.println("No entries for patient with id of " + authorId);
		}
		
		HibernateUtil.closeSession();
		return null;
		
	}
	
	// find all entries (public & private) from specific author and assigned professional
	public List<Entry> professionalFindAllEntriesByAuthor(User author, User professional) {
		
		Session ses = HibernateUtil.getSession();
		String HQL = "FROM Entry E WHERE E.author = :author AND :professional in elements(E.author.assignedProfessionals)";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("author", author);
		query.setParameter("professional", professional);
		
		try {
			List<Entry> entryList = query.getResultList();
			HibernateUtil.closeSession();
			return entryList;
		} catch (NoResultException nre) {
			System.out.println("No entries for patient with id of " + author.getId());
		}
		
		HibernateUtil.closeSession();
		return null;
		
	}
	
	// find all entries (public & private) from user
	public List<Entry> findAllEntriesByUser(int userId) {
		
		Session ses = HibernateUtil.getSession();
		String HQL = "FROM Entry E WHERE E.author.id = :user_id";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("user_id", userId);
		
		try {
			List<Entry> entryList = query.getResultList();
			HibernateUtil.closeSession();
			return entryList;
		} catch (NoResultException nre) {
			System.out.println("No entries for patient with id of " + userId);
		}
		
		HibernateUtil.closeSession();
		return null;
	}
	
	// find entry from entry id & user (author)
	public Entry patientFindEntryById(int entryId, User user) {
		Session ses = HibernateUtil.getSession();
		String HQL = "FROM Entry E WHERE E.id = :entry_id AND E.author = :author";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("entry_id", entryId);
		query.setParameter("author", user);
		
		try {
			Entry entry = (Entry) query.getSingleResult();
			HibernateUtil.closeSession();
			return entry;
		} catch (NoResultException nre) {
			System.out.println("Could not get entry with id " + entryId);
		}
		
		HibernateUtil.closeSession();
		return null;
	}
	
	// find entry from entry id & user (assigned professional)
	public Entry professionalFindEntryById(int entryId, User user) {
		Session ses = HibernateUtil.getSession();
		String HQL = "FROM Entry E WHERE E.id = :entry_id AND :user in elements(E.author.assignedProfessionals)";
		
		Query query = ses.createQuery(HQL);
		query.setParameter("entry_id", entryId);
		query.setParameter("user", user);
		
		try {
			Entry entry = (Entry) query.getSingleResult();
			HibernateUtil.closeSession();
			return entry;
		} catch (NoResultException nre) {
			System.out.println("Could not get entry with id " + entryId);
		}
		
		HibernateUtil.closeSession();
		return null;
	}
	
	// insert entry
	public Entry insertEntry(Entry entry, User user) {
		
		// open Session to connect to database
		Session ses = HibernateUtil.getSession();
		
		// if user isn't a patient return null
		if (user.isProfessional()) {
			System.out.println("User with id " + user.getId() + " is a professional"); // TODO: log instead of print
			HibernateUtil.closeSession();
			return null;
		}
		
		// add user to entry
		entry.setAuthor(user);
		
		// get current date
		Date date = new Date();
		
		// set date on entry
		entry.setDatePosted(date);
		
		// save entry to database
		ses.save(entry);
		
		// close Session
		HibernateUtil.closeSession();
		
		return entry;
	}
	
	
}
