package com.revature.entry;

import java.sql.Timestamp;

import com.revature.user.User;

public class Entry {
	
	private int id;
	private String title;
	private String body;
	private Timestamp date_posted;
	private boolean isPublic;
	private String tags;
	private User author;
	private int sentiment;
	
	// no args constructor
	public Entry() {
		super();
	}

	// all args constructor
	public Entry(int id, String title, String body, Timestamp date_posted, boolean isPublic, String tags, User author,
			int sentiment) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.date_posted = date_posted;
		this.isPublic = isPublic;
		this.tags = tags;
		this.author = author;
		this.sentiment = sentiment;
	}

	// all args constructor without id
	public Entry(String title, String body, Timestamp date_posted, boolean isPublic, String tags, User author,
			int sentiment) {
		super();
		this.title = title;
		this.body = body;
		this.date_posted = date_posted;
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

	public Timestamp getDate_posted() {
		return date_posted;
	}

	public void setDate_posted(Timestamp date_posted) {
		this.date_posted = date_posted;
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
		result = prime * result + ((date_posted == null) ? 0 : date_posted.hashCode());
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
		if (date_posted == null) {
			if (other.date_posted != null)
				return false;
		} else if (!date_posted.equals(other.date_posted))
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
		return "Entry [id=" + id + ", title=" + title + ", body=" + body + ", date_posted=" + date_posted
				+ ", isPublic=" + isPublic + ", tags=" + tags + ", author=" + author + ", sentiment=" + sentiment + "]";
	}
	
}
