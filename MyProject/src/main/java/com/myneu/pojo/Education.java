package com.myneu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Education")
public class Education {
	
	
	@Id @GeneratedValue
	@Column(name="id", unique = true, nullable = false)
	private long id;
	

	@ManyToOne
	@JoinColumn(name="userId")
	private Candidate candidate;

	
	private String candidateEmail;	
	private String underGradDegreeName;
	private String underGradattendedFromDate;
	private String underGradattendedToDate;
	private String schoolName;
	private String schoolattendedFromDate;
	private String schoolattendedToDate;
	private String gradDegreeName;
	private String gradattendedFromDate;
	private String gradattendedToDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public String getCandidateEmail() {
		return candidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getUnderGradDegreeName() {
		return underGradDegreeName;
	}
	public void setUnderGradDegreeName(String underGradDegreeName) {
		this.underGradDegreeName = underGradDegreeName;
	}
	public String getUnderGradattendedFromDate() {
		return underGradattendedFromDate;
	}
	public void setUnderGradattendedFromDate(String underGradattendedFromDate) {
		this.underGradattendedFromDate = underGradattendedFromDate;
	}
	public String getUnderGradattendedToDate() {
		return underGradattendedToDate;
	}
	public void setUnderGradattendedToDate(String underGradattendedToDate) {
		this.underGradattendedToDate = underGradattendedToDate;
	}
	public String getSchoolattendedFromDate() {
		return schoolattendedFromDate;
	}
	public void setSchoolattendedFromDate(String schoolattendedFromDate) {
		this.schoolattendedFromDate = schoolattendedFromDate;
	}
	public String getSchoolattendedToDate() {
		return schoolattendedToDate;
	}
	public void setSchoolattendedToDate(String schoolattendedToDate) {
		this.schoolattendedToDate = schoolattendedToDate;
	}
	public String getGradDegreeName() {
		return gradDegreeName;
	}
	public void setGradDegreeName(String gradDegreeName) {
		this.gradDegreeName = gradDegreeName;
	}
	public String getGradattendedFromDate() {
		return gradattendedFromDate;
	}
	public void setGradattendedFromDate(String gradattendedFromDate) {
		this.gradattendedFromDate = gradattendedFromDate;
	}
	public String getGradattendedToDate() {
		return gradattendedToDate;
	}
	public void setGradattendedToDate(String gradattendedToDate) {
		this.gradattendedToDate = gradattendedToDate;
	}
	public String getSchoolName() {
		return schoolName;
	}
		
	

}
