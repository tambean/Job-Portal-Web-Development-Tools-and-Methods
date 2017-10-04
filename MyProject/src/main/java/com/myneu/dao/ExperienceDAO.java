package com.myneu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myneu.pojo.Candidate;
import com.myneu.pojo.Education;
import com.myneu.pojo.Experience;
import com.yusuf.spring.exception.AdException;

@Repository
public class ExperienceDAO extends DAO {

	public ExperienceDAO() {
	}

	public Experience createexperience(String companyName, String startDate, String endDate, String designation,
			String responsibilities, String candiateEmail, Candidate c) throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Experience experience = new Experience();
			experience.setCompanyName(companyName);
			experience.setStartDate(startDate);
			experience.setEndDate(endDate);
			experience.setDesignation(designation);
			experience.setResponsibilities(responsibilities);
			experience.setCandidateEmail(candiateEmail);
			experience.setCandidate(c);

			getSession().save(experience);

			commit();
			return experience;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create experience" + emailId,
			// e);
			throw new AdException("Exception while creating experience: " + e.getMessage());
		}
	}

	public List listExperience(String candidateEmail) throws AdException {
		try {
			begin();
			System.out.println("listExperience" + candidateEmail);
			Query q = getSession().createQuery("from Experience where candidateEmail = :candidateEmail");
			q.setString("candidateEmail", candidateEmail);
			System.out.println("listExperience" + q);
			List list = q.list();
			System.out.println("in DAO listExperience Array Size" + list.size());
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e.getMessage());
			throw new AdException("Could not list the Experience details", e);

		}
	}

	public Experience getExperience(String candidateEmail, String expID) throws AdException {

		try {
			begin();
			System.out.println("getExperience--" + candidateEmail);
			System.out.println("getExperience---" + expID);
			int id = Integer.parseInt(expID.trim());
			System.out.println("getExperience--" + id);
			Query q = getSession().createQuery("from Experience where id = :id");
			q.setInteger("id", id);
			// q.setString("candidateEmail", candidateEmail);
			System.out.println("getExperience" + q);
			Experience exp = (Experience) q.uniqueResult();
			commit();
			return exp;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e.getMessage());
			throw new AdException("Could not list the Experience details", e);

		}

	}

	public void UpdateExperience(int id,Experience exp) throws AdException {
	        try {
	        	String companyName= null;
	        	String designation = null;
	        	String responsibilities = null;
	        	String startDate = null;
	        	String endDate = null;
	            begin();	            
	            System.out.println("UpdateExperience  " +id);
	            companyName = exp.getCompanyName();
	            designation = exp.getDesignation();
	            responsibilities = exp.getResponsibilities();
	            startDate = exp.getStartDate();
	            endDate = exp.getEndDate();
	            	                
	            Query q = getSession().createQuery("update Experience set companyName = :companyName,"
	            		+ "designation = :designation,"
	            		+ "responsibilities = :responsibilities,"
	            		+ "startDate = :startDate,"
	            		+ "endDate = :endDate where id = :id");
	            System.out.println("UpdateExperience  " +q);
	            q.setInteger("id", id);
	            q.setString("companyName", companyName);
	            q.setString("designation", designation);
	            q.setString("responsibilities", responsibilities);
	            q.setString("startDate", startDate);
	            q.setString("endDate", endDate);
	            System.out.println("UpdateExperience  " +q);
	            int i = q.executeUpdate();
	            commit();	    
	        } catch (HibernateException e) {
	            rollback();
	            System.out.println(e.getMessage());
	            throw new AdException("Could not list the UpdateExperience details", e);
	    
	        }
	    }

}
