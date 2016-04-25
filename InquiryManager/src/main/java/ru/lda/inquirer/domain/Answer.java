package ru.lda.inquirer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ANSWER")

public class Answer {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="QUESTION_ID")
	private Question question;
	
	@Column(name = "BODY")
	private String body;
	
	@Column(name = "VALID")
	private Boolean valid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
}