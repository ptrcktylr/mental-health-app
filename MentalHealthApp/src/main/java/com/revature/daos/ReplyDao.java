package com.revature.daos;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;

import com.revature.models.Entry;
import com.revature.models.Reply;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

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
	public Reply insertReply(Reply reply, User user) {
		
		// open Session to connect to database
		Session sess = HibernateUtil.getSession();
				
		reply.setAuthor(user);
				
		// get current date
		Timestamp date = new Timestamp(10);
				
		reply.setDatePosted(date);
				
		// save entry to database
		sess.save(reply);
				
		HibernateUtil.closeSession();
				
		return reply;
	}
	
	// delete a reply
	public void deleteReply(Reply reply) {
		
		Session sess = HibernateUtil.getSession();
		
		try {
			sess.remove(reply);
		}
		catch(IllegalArgumentException e) {
			System.out.println("No reply with id = " + reply.getId());
		}
		
		HibernateUtil.closeSession();
	}
}
