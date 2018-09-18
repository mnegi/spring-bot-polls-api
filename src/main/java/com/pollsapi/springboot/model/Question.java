package com.pollsapi.springboot.model;
import java.util.Date;
	
public class Question {

	private long id;
	private String url;
	private published_at Date;
	private String question;
	private Choice[] choices;

	public Question(){
		id=0;
	}
	
	public Question(long id, String question, Choice[] choices){
		this.id = id;
		this.question = question;
		this.url = "/questions/" + id;
		this.published_at = new Date();
		this.choices = choices;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void getQuestion(String question) {
		this.question = question;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getPublishedAt() {
		return published_at;
	}

	public void setPublishedAt(Date published_at) {
		this.published_at = published_at;
	}

	public Choices[] getChoices() {
		return choices;
	}

	public void setChoices(Choices[] choices) {
		this.choices = choices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", url=" + url
				+ ", published_at=" + published_at + "]";
	}


}
