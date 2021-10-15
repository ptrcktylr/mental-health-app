package com.revature.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.Entry;
import com.revature.models.Reply;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

@Repository
public class ReplyDao {
	
	// get all replies
	public List<Reply> findAllReplies() {
		
		// open a session
		Session sess = HibernateUtil.getSession();
		
		// get all the replies from the db
		@SuppressWarnings("unchecked")
		List<Reply> replyList = sess.createQuery("FROM Reply").list();
		
		// close the session
		HibernateUtil.closeSession();
		
		return replyList;
	}
	
	// get all replies for a given entry
	public List<Reply> findAllRepliesByEntry(Entry entry) {
		
		// create a session
		Session sess = HibernateUtil.getSession();
		
		// create the HQL string
		String HQL = "FROM Reply WHERE Reply.Entry = :entry";
		
		// create the query from the HQL and set the parameter
		Query q = sess.createQuery(HQL);
		q.setParameter("entry", entry);
		
		try {
			@SuppressWarnings("unchecked")
			List<Reply> replyList = q.getResultList();
			
			HibernateUtil.closeSession();
			
			return replyList;
		} catch (NoResultException e) {
			System.out.println("No replies for entry with id of " + entry.getId());
		}
		
		HibernateUtil.closeSession();
		
		return null;
	}
	
	// insert a reply
	public Reply insertReply(Reply reply, Entry entry, User user) {
		
		// open Session to connect to database
		Session sess = HibernateUtil.getSession();
		
		// add user to reply
		reply.setAuthor(user);
		
		// get current date
		Date date = new Date();
		
		// add date to reply
		reply.setDatePosted(date);
		
		// add reply to entry
		reply.setEntry(entry);
		
		// save entry to database
		sess.save(reply);
				
		HibernateUtil.closeSession();
				
		return reply;
	}
	
	// delete a reply
	public boolean deleteReply(Reply reply, User author) {
		
		// open Session to connect to database
		Session sess = HibernateUtil.getSession();
		boolean success = false;
		
		// delete must happen within a transaction
		Transaction tran = sess.beginTransaction();
			
		// create HQL
		String HQL = "DELETE Reply R WHERE R = :reply AND R.author = :author";
			
		Query query = sess.createQuery(HQL);
		query.setParameter("reply", reply);
		query.setParameter("author", author);
			
		try {
			// execute query
			query.executeUpdate();
			
			// commit changes
			tran.commit();
			success = true;
		}
		catch(Exception e) {
			System.out.println("Unable to delete reply with id " + reply.getId());
		}
		
		HibernateUtil.closeSession();
		return success;
	}
	
	public boolean professionalDeleteReply(Reply reply, User professional) {
		Session ses = HibernateUtil.getSession();
		boolean success = false;
		
		if (professional.isProfessional()) {
			Transaction tran = ses.beginTransaction();
			
			String HQL = "DELETE Reply R WHERE R = :reply";
			
			Query query = ses.createQuery(HQL);
			query.setParameter("reply", reply);
			
			try {
				query.executeUpdate();
				tran.commit();
				success = true;
			} catch (Exception e) {
				System.out.println("Unable to delete reply with id " + reply.getId());
			}
			
			return success;
		} else {
			System.out.println("Unable to delete reply with id " + reply.getId());
			return success;
		}
		
		
	}

	
}
