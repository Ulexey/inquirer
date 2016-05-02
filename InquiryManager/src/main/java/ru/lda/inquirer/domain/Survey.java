package ru.lda.inquirer.domain;

import java.util.Date;
import java.util.List;
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
@Table(name="SURVEY")

public class Survey {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="INQUIRY_ID")
	private Inquiry inquiry;
	
	@Column(name = "FIO")
	private String fio;

	
	@Column(name = "START")
	private Date start;

	@Column(name = "STOP")
	private Date stop;
	
	@Column(name = "STATUS")
	private String status;

	@OneToMany (mappedBy="survey")
	@OrderBy (value = "id")
	private List<Result> results;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStop() {
		return stop;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	

}
