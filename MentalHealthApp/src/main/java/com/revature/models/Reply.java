package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="replies")
public class Reply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="body", nullable=false, columnDefinition="TEXT")
	private String body;
	
	@Column(name="date_posted", nullable=false, 
			columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Timestamp datePosted;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="author_id", referencedColumnName="id", nullable=false)
	private User author;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="entry_id", referencedColumnName="id", nullable=false)
	private Entry entry;
	
	public Reply() {
		super();
	}

	public Reply(int id, String body, Timestamp datePosted, User author, Entry entry) {
		super();
		this.id = id;
		this.body = body;
		this.datePosted = datePosted;
		this.author = author;
		this.entry = entry;
	}

	public Reply(String body, Timestamp datePosted, User author, Entry entry) {
		super();
		this.body = body;
		this.datePosted = datePosted;
		this.author = author;
		this.entry = entry;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Timestamp datePosted) {
		this.datePosted = datePosted;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((datePosted == null) ? 0 : datePosted.hashCode());
		result = prime * result + ((entry == null) ? 0 : entry.hashCode());
		result = prime * result + id;
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
		Reply other = (Reply) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (datePosted == null) {
			if (other.datePosted != null)
				return false;
		} else if (!datePosted.equals(other.datePosted))
			return false;
		if (entry == null) {
			if (other.entry != null)
				return false;
		} else if (!entry.equals(other.entry))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", body=" + body + ", datePosted=" + datePosted + ", author=" + author + ", entry="
				+ entry + "]";
	}
	
}
