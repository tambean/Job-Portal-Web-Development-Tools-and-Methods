package com.myneu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Jobs {
	
	@Id @GeneratedValue
	@Column(name="jobID", unique = true, nullable = false)
	private int jobID;	
	
	@ManyToOne
	@JoinColumn(name="userid")
	private Employer employer;
	
	@Column(name="jobTitle")
	private String jobTitle;
	
	@Column(name="jobDescription")
	private String jobDescription;
	
	@Column(name="jobType")
	private String jobType;
	
	@Column(name = "employerName")
	private String employerName;

	@Column(name = "experienceRequired")
	private int experienceRequired;
	
	@Column(name = "skillsRequired")
	private String skillsRequired;
	
	@Column(name = "qualificationRequired")
	private String qualificationRequired;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "postedOn")
	private String postedOn;
	
	@JoinColumn(name = "userID")
	private String postedBy;

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}



	public int getExperienceRequired() {
		return experienceRequired;
	}

	public void setExperienceRequired(int experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	public String getSkillsRequired() {
		return skillsRequired;
	}

	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public String getQualificationRequired() {
		return qualificationRequired;
	}

	public void setQualificationRequired(String qualificationRequired) {
		this.qualificationRequired = qualificationRequired;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	
	
	
	

}
