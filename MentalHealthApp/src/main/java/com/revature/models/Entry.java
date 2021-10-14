package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
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
@Table(name="entries")
public class Entry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="body", nullable=false, columnDefinition="TEXT")
	private String body;
	
	@Column(name="date_posted", nullable=false, 
			columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Timestamp datePosted;
	
	@Column(name="is_public", nullable=false)
	private boolean isPublic;
	
	@Column(name="tags")
	private String tags;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="author_id", nullable=false)
	private User author;
	
	@Column(name="sentiment")
	private int sentiment;
	
	// no args constructor
	public Entry() {
		super();
	}

	// all args constructor
	public Entry(int id, String title, String body, Timestamp datePosted, boolean isPublic, String tags, User author,
			int sentiment) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.datePosted = datePosted;
		this.isPublic = isPublic;
		this.tags = tags;
		this.author = author;
		this.sentiment = sentiment;
	}

	// all args constructor without id
	public Entry(String title, String body, Timestamp datePosted, boolean isPublic, String tags, User author,
			int sentiment) {
		super();
		this.title = title;
		this.body = body;
		this.datePosted = datePosted;
		this.isPublic = isPublic;
		this.tags = tags;
		this.author = author;
		this.sentiment = sentiment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getSentiment() {
		return sentiment;
	}

	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((datePosted == null) ? 0 : datePosted.hashCode());
		result = prime * result + id;
		result = prime * result + (isPublic ? 1231 : 1237);
		result = prime * result + sentiment;
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Entry other = (Entry) obj;
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
		if (id != other.id)
			return false;
		if (isPublic != other.isPublic)
			return false;
		if (sentiment != other.sentiment)
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", title=" + title + ", body=" + body + ", datePosted=" + datePosted
				+ ", isPublic=" + isPublic + ", tags=" + tags + ", author=" + author + ", sentiment=" + sentiment + "]";
	}
	
}
