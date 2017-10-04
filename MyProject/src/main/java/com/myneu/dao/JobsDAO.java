package com.myneu.dao;
import org.hibernate.Query;

import org.hibernate.HibernateException;

import com.myneu.pojo.Employer;
import com.myneu.pojo.Jobs;
import com.yusuf.spring.exception.AdException;

public class JobsDAO extends DAO {
	
	public Jobs createjob(String jobTitle, String jobDescription, String jobType, int experience,
							String skills, String qualification, String location,
							String postedOn, String postedBy, Employer emp, String employerName)
            throws AdException {
        try {
            begin();
            System.out.println("inside JOBD DAO");
            Jobs job = new Jobs();
            job.setEmployerName(employerName);
            job.setExperienceRequired(experience);
            job.setJobDescription(jobDescription);
            job.setJobTitle(jobTitle);
            job.setJobType(jobType);
            job.setLocation(location);
            job.setPostedBy(postedBy);
            job.setPostedOn(postedOn);
            job.setQualificationRequired(qualification);
            job.setSkillsRequired(skills);
            getSession().save(job);            
            commit();
            return job;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Exception while creating job: " + e.getMessage());
        }
    }
	
	public void updateJob(int jobID,Jobs job)throws AdException{
		try {
            begin();
            System.out.println("inside updateJob DAO");
            int experienceRequired = job.getExperienceRequired();
            String jobDescription = job.getJobDescription();
            String jobTitle = job.getJobTitle();
            String location = job.getLocation();
            //String postedOn = job.getPostedOn();
            String postedBy = job.getPostedBy();
            String jobType = job.getJobType();
            String skillsRequired = job.getSkillsRequired();
            String qualificationRequired = job.getQualificationRequired();                    
           
            Query q = getSession().createQuery("update Jobs set experienceRequired = :experienceRequired,"
            		+ "jobDescription = :jobDescription,"
            		+ "jobTitle = :jobTitle,"
            		+ "location = :location,"
               		+ "postedBy = :postedBy,"
            		+ "jobType = :jobType,"
            		+ "skillsRequired = :skillsRequired,"
            			+ "qualificationRequired = :qualificationRequired where jobID = :jobID");
            System.out.println("updateJob  " +q);
            q.setInteger("jobID", jobID);
            q.setInteger("experienceRequired", experienceRequired);
            q.setString("jobDescription", jobDescription);
            q.setString("jobTitle", jobTitle);
            q.setString("location", location);
            //q.setString("postedOn", postedOn);
            q.setString("postedBy", postedBy);
            q.setString("jobType", jobType);
            q.setString("skillsRequired", skillsRequired);
            q.setString("qualificationRequired", qualificationRequired);
            System.out.println("updateJob  " +q);
            int i = q.executeUpdate();
            commit();	    
         } catch (HibernateException e) {
            rollback();
            throw new AdException("Exception while creating job: " + e.getMessage());
        }
		
	}
	
	public Jobs getJob(int jobID)throws AdException{
		try {
            begin();
            System.out.println("inside getJob DAO");
            Jobs job = new Jobs();
            Query q = getSession().createQuery("from Jobs where jobID = :jobID");
            q.setInteger("jobID", jobID);
            job = (Jobs)q.uniqueResult();
            commit();
            return job;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Exception while creating job: " + e.getMessage());
        }
		
	}

}
