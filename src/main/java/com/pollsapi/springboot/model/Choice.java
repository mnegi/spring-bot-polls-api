package com.websystique.springboot.model;
	
public class Choice {
	private long id;
    private String url;
    private long questionId;
	private int votes;
	private String choice;

	public Choice(){
		id=0;
	}
	
	public Choice(long id, long questionId, int votes, String choice){
        this.id = id;
        this.questionId = questionId;
        this.url = "/questions/"+ questionId + "/choices/" + id;
		this.votes = votes;
		this.choice = choice;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
    }
    
    public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public String getChoice() {
		return choice;
	}

	public void getChoice(String choice) {
		this.choice = choice;
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
		Choice other = (Choice) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Choice [id=" + id + ", choice=" + choice + ", votes=" + votes
				+ "]";
	}


}
