package com.myneu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Repository
public class LoginDAO extends DAO {
	
	
	 public UserAccount getUserAccount(String emailID, String password) throws AdException{
		  
	        try {
	            begin();
	            Query q = getSession().createQuery("from UserAccount where emailID = :emailID AND password = :password");
	            q.setString("emailID", emailID);
	            q.setString("password", password);
	            UserAccount useraccount = (UserAccount) q.uniqueResult();
	            commit();
	            return useraccount;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + emailID, e);
	        }
	 }
	 
	 public UserAccount getUserAccount(String emailID) throws AdException{
   	  
	        try {
	            begin();
	            Query q = getSession().createQuery("from UserAccount where emailID = :emailID");
	            q.setString("emailID", emailID);
	            UserAccount useraccount = (UserAccount) q.uniqueResult();
	            commit();
	            return useraccount;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + emailID, e);
	        }
	    }
	 
	 public int UpdateCandidateUserEdu(String emailID) throws AdException{
	   	  
	        try {
	        	String type = "candidateEdu";
	            begin();
	            Query q = getSession().createQuery("update UserAccount set type =:type where emailID = :emailID");
	            q.setString("emailID", emailID);
	            q.setString("type", type);
	            int i = q.executeUpdate();
	            commit();
	          return i;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + emailID, e);
	        }
	    }
	 
	 public boolean checkIfEmailExists(String emailId) throws AdException{
	      
	    	boolean result = false;
	        try{
	        	begin();
	        
	        	Query q = getSession().createQuery("from UserAccount where emailId = :emailId");
	        	q.setString("emailId",emailId);
	        	List list = q.list();	        	
	        	commit();
	            if(!list.isEmpty()) {
	            	result = true;	            	
	            }	           
	        }catch(HibernateException e) {
	            rollback();
	            throw new AdException("Exception while creating user: " + e.getMessage());
	        }
	        return result;
	   }

}
