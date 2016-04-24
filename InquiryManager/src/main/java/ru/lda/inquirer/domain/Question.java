package ru.lda.inquirer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="QUESTION")


public class Question {
	
	@Id
	@Column(name = "QUESTION_ID")
	@GeneratedValue
	private Long id;
	
	
	@Column(name = "BODY")
	private String body;

	
	@Column(name = "MULTIVALUE")
	private Boolean multivalue;
	
	
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


	public Boolean getMultivalue() {
		return multivalue;
	}

	public void setMultivalue(Boolean multivalue) {
		this.multivalue = multivalue;
	}

	
	

}
