package ru.lda.inquirer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QUESTIONS")


public class Question {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@Column(name = "BODY")
	private String body;

	
	@Column(name = "SINGLE")
	private Boolean single;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Boolean getSingle() {
		return single;
	}

	public void setSingle(Boolean single) {
		this.single = single;
	}
	
	

}
