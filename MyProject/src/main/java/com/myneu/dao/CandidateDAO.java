package com.myneu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myneu.pojo.Candidate;
import com.myneu.pojo.ProcessApplications;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Repository
public class CandidateDAO extends DAO {

	public CandidateDAO() {

	}

	public Candidate create(Candidate c) throws AdException {
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

	public Candidate getCandidate(UserAccount userAccount) throws AdException {
		try {
			begin();
			System.out.println("inside getCandidate");
			int userId = userAccount.getUserId();
			Query q = getSession().createQuery("from UserAccount where userId = :userId");
			q.setInteger("userId", userId);
			Candidate candidate = (Candidate) q.uniqueResult();
			commit();
			return candidate;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e.getMessage());
			throw new AdException("Could not list the userAccount", e);

		}
	}
	
	public ProcessApplications applyJob(String sender, String receiver,
										Candidate c, String employerName, int jobId)
			throws AdException {
		try {
			begin();
			System.out.println("inside ProcessApplications DAO");
			ProcessApplications jobApp = new ProcessApplications();
			jobApp.setSender(sender);
			jobApp.setReceiver(receiver);
			jobApp.setStatus("SENT");
			jobApp.setCandidate(c);
			jobApp.setJobID(jobId);
			jobApp.setEmployerName(employerName);
			getSession().save(jobApp);
			commit();
			return jobApp;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while applying: " + e.getMessage());
		}
	}
	
	public ProcessApplications applyJobRequest(String sender, String receiver, Candidate c,
			String employerName,
			int jobId) throws AdException {
		try {
			begin();
			System.out.println("inside applyJobRequest DAO");
			ProcessApplications request = new ProcessApplications();
			request.setSender(sender);
			request.setReceiver(receiver);
			request.setStatus("SENT");
			request.setCandidate(c);
			request.setJobID(jobId);
			request.setEmployerName(employerName);
			getSession().save(request);
			commit();
			return request;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while applyin job: " + e.getMessage());
		}
	}
	
	public String getCandidateSkills(UserAccount userAccount) throws AdException {
		try {
			String skill = null;
			begin();
			System.out.println("inside getCandidateSkills");
			int userId = userAccount.getUserId();
			Query q = getSession().createQuery("from Candidate where userId = :userId");
			q.setInteger("userId", userId);
			System.out.println("inside getCandidateSkills"+userId);
			Candidate candidate = (Candidate) q.uniqueResult();
			commit();
			skill = candidate.getSkill();
			System.out.println("inside getCandidateSkills"+skill);
			return skill;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e.getMessage());
			throw new AdException("Could not list the userAccount", e);

		}
	}
	
	public void updateCandidateSkills(UserAccount userAccount,String skill) throws AdException {
		try {
			
			begin();
			System.out.println("inside updateCandidateSkills");
			int userId = userAccount.getUserId();
			System.out.println("inside updateCandidateSkills" +userId);
			Query q = getSession().createQuery("update Candidate set skill = :skill where userId = :userId");
			q.setInteger("userId", userId);
			q.setString("skill", skill);
			int i = q.executeUpdate();
			commit();		
		} catch (HibernateException e) {
			rollback();
			System.out.println(e.getMessage());
			throw new AdException("Could not list the userAccount", e);

		}
	}

}
