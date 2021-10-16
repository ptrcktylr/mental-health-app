package com.revature.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="entries")
public class Entry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="entry_id")
	private int id;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="body", nullable=false, columnDefinition="TEXT")
	private String body;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_posted",
			columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date datePosted;
	
	private boolean isPublic;
	
	private String tag;
	
	private int sentiment;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="patient_id")
	@JsonIgnore
	private Patient patient;
	
	@OneToMany(mappedBy="entry", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Reply> replies;

	public Entry() {
		super();
	}

	public Entry(int id, String title, String body, Date datePosted, boolean isPublic, String tag, int sentiment,
			Patient patient, List<Reply> replies) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.datePosted = datePosted;
		this.isPublic = isPublic;
		this.tag = tag;
		this.sentiment = sentiment;
		this.patient = patient;
		this.replies = replies;
	}

	public Entry(String title, String body, Date datePosted, boolean isPublic, String tag, int sentiment,
			Patient patient, List<Reply> replies) {
		super();
		this.title = title;
		this.body = body;
		this.datePosted = datePosted;
		this.isPublic = isPublic;
		this.tag = tag;
		this.sentiment = sentiment;
		this.patient = patient;
		this.replies = replies;
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

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getSentiment() {
		return sentiment;
	}

	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((datePosted == null) ? 0 : datePosted.hashCode());
		result = prime * result + id;
		result = prime * result + (isPublic ? 1231 : 1237);
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((replies == null) ? 0 : replies.hashCode());
		result = prime * result + sentiment;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (replies == null) {
			if (other.replies != null)
				return false;
		} else if (!replies.equals(other.replies))
			return false;
		if (sentiment != other.sentiment)
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
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
		return "Entry [id=" + id + ", title=" + title + ", body=" + body + ", datePosted=" + datePosted + ", isPublic="
				+ isPublic + ", tag=" + tag + ", sentiment=" + sentiment + ", patient=" + patient + ", replies="
				+ replies + "]";
	}
	
}
