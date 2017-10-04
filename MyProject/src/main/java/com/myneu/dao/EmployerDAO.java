package com.myneu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


import com.myneu.pojo.Employer;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Repository
public class EmployerDAO extends DAO {

	public EmployerDAO() {

	}

	public Employer create(Employer c) throws AdException {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(c);

			commit();
			return c;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + emailId, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
	}
	
	 public Employer getEmployer(UserAccount userAccount)throws AdException {
    	 try {
             begin();
             int userId = userAccount.getUserId();
             Query q = getSession().createQuery("from UserAccount where userId = :userId");
             q.setInteger("userId",userId);
             Employer employer = (Employer) q.uniqueResult();
             commit();
             return employer;
         } catch (HibernateException e) {
             rollback();
             System.out.println(e.getMessage());
             throw new AdException("Could not list the userAccount", e);
     
         }
    }
	 
	public List jobApplication(String employer) throws AdException {
		try {
			begin();
			System.out.println("jobApplication" + employer);
			Query q = getSession().createQuery("from ProcessApplications where receiver = :employer");
			q.setString("employer", employer);
			List list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e.getMessage());
			throw new AdException("Could not list the applications", e);

		}
	}
	
	public List list() throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Jobs");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            System.out.println(e.getMessage());
            throw new AdException("Could not list the jobs", e);
    
        }
    }
	
    public List list(String emailId) throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Jobs where postedBy = :emailId");
            q.setString("emailId",emailId);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            System.out.println(e.getMessage());
            throw new AdException("Could not list the jobs", e);
    
        }
    }    
    
	public List listAppliedJob(String candidate) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from ProcessApplications where sender = :candidate");
			q.setString("candidate", candidate);
			List list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e.getMessage());
			throw new AdException("Could not list the applications", e);

		}
	}

}
