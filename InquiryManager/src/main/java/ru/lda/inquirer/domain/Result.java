package ru.lda.inquirer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="RESULT")

public class Result {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="ANSWER_ID")
	private Answer answer;

	@ManyToOne
    @JoinColumn(name="SURVEY_ID")
	private Survey survey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

}