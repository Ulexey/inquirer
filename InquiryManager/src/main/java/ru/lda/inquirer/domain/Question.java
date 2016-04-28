package ru.lda.inquirer.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="QUESTION")

public class Question {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="INQUIRY_ID")
	private Inquiry inquiry;
	
	@Column(name = "BODY")
	private String body;
	
	@Column(name = "MULTIVALUE")
	private Boolean multivalue;
	
	@OneToMany (mappedBy="question",cascade = CascadeType.ALL)
	@OrderBy (value = "id")
	private List<Answer> answers;
	
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

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public Boolean getMultivalue() {
		return multivalue;
	}

	public void setMultivalue(Boolean multivalue) {
		this.multivalue = multivalue;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	

}
