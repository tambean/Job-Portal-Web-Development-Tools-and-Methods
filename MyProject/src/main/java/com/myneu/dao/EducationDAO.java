package com.myneu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myneu.pojo.Candidate;
import com.myneu.pojo.Education;
import com.yusuf.spring.exception.AdException;

@Repository
public class EducationDAO extends DAO {

	public EducationDAO(){	
	}
	
	 public Education createeducation(String gradattendedFromDate,
			 String gradattendedToDate, String gradDegreeName,
			 String schoolattendedFromDate, String schoolattendedToDate, String schoolName,
			 String underGradattendedFromDate,String underGradattendedToDate,String underGradDegreeName,
			 String candiateEmail,Candidate c)
	            throws AdException {
	        try {
	            begin();
	            System.out.println("inside createeducation DAO");
	            
	            Education education=new Education();
	            education.setSchoolName(schoolName);
	            education.setGradattendedFromDate(gradattendedFromDate);
	            education.setGradattendedToDate(gradattendedToDate);
	            education.setGradDegreeName(gradDegreeName);
	            education.setSchoolattendedFromDate(schoolattendedFromDate);
	            education.setSchoolattendedToDate(schoolattendedToDate);
	            education.setUnderGradattendedFromDate(underGradattendedFromDate);
	            education.setUnderGradattendedToDate(underGradattendedToDate);
	            education.setUnderGradDegreeName(underGradDegreeName);
	            education.setCandidateEmail(candiateEmail);
	            education.setCandidate(c);  	            
	            getSession().save(education);	            
	            commit();
	            return education;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create education" + emailId, e);
	            throw new AdException("Exception while creating education: " + e.getMessage());
	        }
	    }
	 
	 
	    public Education getEducation(String candidateEmail) throws AdException {
	        try {
	            begin();
	            System.out.println("listEducation  " +candidateEmail);
	            Query q = getSession().createQuery("from Education where candidateEmail = :candidateEmail");
	            q.setString("candidateEmail", candidateEmail);
	            System.out.println("listEducation  " +q);
	            Education list = (Education)q.uniqueResult();	            
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            System.out.println(e.getMessage());
	            throw new AdException("Could not list the eduation details", e);
	    
	        }
	    }
	    public List listEducation(String candidateEmail) throws AdException {
	        try {
	            begin();
	            System.out.println("listEducation  " +candidateEmail);
	            Query q = getSession().createQuery("from Education where candidateEmail = :candidateEmail");
	            q.setString("candidateEmail", candidateEmail);
	            System.out.println("listEducation  " +q);
	            List list = q.list();	            
	            commit();
	            return list;
	        } catch (HibernateException e) {
	            rollback();
	            System.out.println(e.getMessage());
	            throw new AdException("Could not list the eduation details", e);
	    
	        }
	    }
	    
	    public void UpdateEducation(int userId,Education edu) throws AdException {
	        try {
	        	String underGradDegreeName= null;
	        	String underGradattendedFromDate = null;
	        	String underGradattendedToDate = null;
	        	String schoolName = null;
	        	String schoolattendedFromDate = null;
	        	String schoolattendedToDate = null;
	        	String gradDegreeName = null;
	        	String gradattendedFromDate = null;
	        	String gradattendedToDate = null;
	            begin();	            
	            System.out.println("UpdateEducation  " +userId);
	            schoolName = edu.getSchoolName();
	            schoolattendedFromDate = edu.getSchoolattendedFromDate();
	            schoolattendedToDate = edu.getSchoolattendedToDate();
	            underGradattendedFromDate = edu.getUnderGradattendedFromDate();
	            underGradattendedToDate = edu.getUnderGradattendedToDate();
	            underGradDegreeName = edu.getUnderGradDegreeName();
	            gradattendedFromDate = edu.getGradattendedFromDate();
	            gradattendedToDate= edu.getGradattendedToDate();
	            gradDegreeName = edu.getGradDegreeName();	                
	            Query q = getSession().createQuery("update Education set schoolName = :schoolName,"
	            		+ "schoolattendedFromDate = :schoolattendedFromDate,"
	            		+ "schoolattendedToDate = :schoolattendedToDate,"
	            		+ "underGradattendedFromDate = :underGradattendedFromDate,"
	            		+ "underGradattendedToDate = :underGradattendedToDate,"
	            		+ "underGradDegreeName = :underGradDegreeName,"
	            		+ "gradattendedFromDate = :gradattendedFromDate,"
	            		+ "gradattendedToDate = :gradattendedToDate,"
	            		+ "gradDegreeName = :gradDegreeName where userId = :userId");
	            System.out.println("UpdateEducation  " +q);
	            q.setInteger("userId", userId);
	            q.setString("schoolName", schoolName);
	            q.setString("schoolattendedFromDate", schoolattendedFromDate);
	            q.setString("schoolattendedToDate", schoolattendedToDate);
	            q.setString("underGradattendedFromDate", underGradattendedFromDate);
	            q.setString("underGradattendedToDate", underGradattendedToDate);
	            q.setString("underGradDegreeName", underGradDegreeName);
	            q.setString("gradattendedFromDate", gradattendedFromDate);
	            q.setString("gradattendedToDate", gradattendedToDate);
	            q.setString("gradDegreeName", gradDegreeName);
	            System.out.println("UpdateEducation  " +q);
	            //List list = q.list();
	            int i = q.executeUpdate();
	            //System.out.println("in DAO Array Size" +list.size());
	            commit();	    
	        } catch (HibernateException e) {
	            rollback();
	            System.out.println(e.getMessage());
	            throw new AdException("Could not list the eduation details", e);
	    
	        }
	    }

}
