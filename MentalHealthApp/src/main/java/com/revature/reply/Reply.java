package com.revature.reply;

import java.sql.Timestamp;

public class Reply {
	
	private int reply_id ;
	private String reply_text;
	private Timestamp reply_date;
	private User user_fk;
	private Role role_fk;
	
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reply(int reply_id, String reply_text, Timestamp reply_date, User user_fk, Role role_fk) {
		super();
		this.reply_id = reply_id;
		this.reply_text = reply_text;
		this.reply_date = reply_date;
		this.user_fk = user_fk;
		this.role_fk = role_fk;
	}

	public Reply(String reply_text, Timestamp reply_date, User user_fk, Role role_fk) {
		super();
		this.reply_text = reply_text;
		this.reply_date = reply_date;
		this.user_fk = user_fk;
		this.role_fk = role_fk;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public String getReply_text() {
		return reply_text;
	}

	public void setReply_text(String reply_text) {
		this.reply_text = reply_text;
	}

	public Timestamp getReply_date() {
		return reply_date;
	}

	public void setReply_date(Timestamp reply_date) {
		this.reply_date = reply_date;
	}

	public User getUser_fk() {
		return user_fk;
	}

	public void setUser_fk(User user_fk) {
		this.user_fk = user_fk;
	}

	public Role getRole_fk() {
		return role_fk;
	}

	public void setRole_fk(Role role_fk) {
		this.role_fk = role_fk;
	}

	@Override
	public String toString() {
		return "Reply [reply_id=" + reply_id + ", reply_text=" + reply_text + ", reply_date=" + reply_date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reply_date == null) ? 0 : reply_date.hashCode());
		result = prime * result + reply_id;
		result = prime * result + ((reply_text == null) ? 0 : reply_text.hashCode());
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
		if (reply_date == null) {
			if (other.reply_date != null)
				return false;
		} else if (!reply_date.equals(other.reply_date))
			return false;
		if (reply_id != other.reply_id)
			return false;
		if (reply_text == null) {
			if (other.reply_text != null)
				return false;
		} else if (!reply_text.equals(other.reply_text))
			return false;
		return true;
	}
	
	

}
