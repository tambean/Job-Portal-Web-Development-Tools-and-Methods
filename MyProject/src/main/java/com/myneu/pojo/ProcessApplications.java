package com.myneu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ProcessApplications {

	@Id @GeneratedValue
	@Column(name="applicationID", unique = true, nullable = false)
	private long applicationID;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Candidate candidate;
	
	//@Transient
	private String employerName;
	//@Transient
	private int jobID;
	//@Transient
	private String sender;
	//@Transient
	private String receiver;
	//@Transient
	private String status;
	public long getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(long applicationID) {
		this.applicationID = applicationID;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
