package com.myneu.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Employer")
@PrimaryKeyJoinColumn(name="userId")
public class Employer extends UserAccount {
	
	
	@Column(name = "employerName")
	private String employerName;
	
	@Column(name = "location")
	private String location;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Jobs> job = new HashSet<Jobs>();

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Jobs> getJob() {
		return job;
	}

	public void setJob(Set<Jobs> job) {
		this.job = job;
	}

	
	
}
