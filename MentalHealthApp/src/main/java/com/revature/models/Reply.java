package com.revature.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="replies")
public class Reply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reply_id")
	private int id;
	
	@Column(name="body", nullable=false, columnDefinition="TEXT")
	private String body;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_posted", nullable=false, 
			columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date datePosted;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="entry_id")
	@JsonIgnore
	private Entry entry;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="professional_id")
	private Professional professional;
	
	public Reply() {
		super();
	}

	public Reply(int id, String body, Date datePosted, Entry entry, Patient patient, Professional professional) {
		super();
		this.id = id;
		this.body = body;
		this.datePosted = datePosted;
		this.entry = entry;
		this.patient = patient;
		this.professional = professional;
	}
	

	public Reply(String body, Date datePosted, Entry entry, Patient patient, Professional professional) {
		super();
		this.body = body;
		this.datePosted = datePosted;
		this.entry = entry;
		this.patient = patient;
		this.professional = professional;
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

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Professional getprofessional() {
		return professional;
	}

	public void setprofessional(Professional professional) {
		this.professional = professional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((datePosted == null) ? 0 : datePosted.hashCode());
		result = prime * result + ((entry == null) ? 0 : entry.hashCode());
		result = prime * result + id;
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((professional == null) ? 0 : professional.hashCode());
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
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (professional == null) {
			if (other.professional != null)
				return false;
		} else if (!professional.equals(other.professional))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", body=" + body + ", datePosted=" + datePosted + ", entry=" + entry + ", patient="
				+ patient + ", professional=" + professional + "]";
	}
	
}
