package ru.lda.inquirer.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import javax.persistence.CascadeType;

@Entity
@Table(name="INQUIRIES")


public class Inquiry {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@Column(name = "SUBJECT")
	private String subject;

	@Column(name = "BODY")
	private String body;
	
	@OneToMany (cascade = CascadeType.ALL)
	@OrderBy (value = "id")
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	
	
}
