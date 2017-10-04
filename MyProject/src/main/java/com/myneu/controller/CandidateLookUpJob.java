package com.myneu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myneu.dao.EmployerDAO;
import com.myneu.pojo.Jobs;
import com.myneu.pojo.ProcessApplications;
import com.yusuf.spring.exception.AdException;

@Controller
public class CandidateLookUpJob {
	
	@Autowired
	EmployerDAO employerDAO;

	@RequestMapping(value = "/lookjob.htm", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Jobs> jobList = new ArrayList();
		List jobaddList = null;

		try {
			jobaddList = employerDAO.list();

			Iterator categIterator = jobaddList.iterator();

			while (categIterator.hasNext()) {
				Jobs job = (Jobs) categIterator.next();
				jobList.add(job);
			}			
			for(Jobs j:jobList){
				System.out.println(j.getEmployerName());
			}
	
		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		ModelAndView mv = new ModelAndView("viewJob", "jobs", jobList);

		return mv;

	}
	
	@RequestMapping(value = "/viewEmployerPostedJob.htm", method = RequestMethod.GET)
	protected ModelAndView handleJobsRequest(HttpServletRequest request2, HttpServletResponse response2)
			throws Exception {
		List<Jobs> jobList = new ArrayList();
		List jobaddList = null;

		String email = request2.getParameter("email");
		try {
			
			jobaddList = employerDAO.list(email);
			Iterator categIterator = jobaddList.iterator();
			while (categIterator.hasNext()) {
				Jobs job = (Jobs) categIterator.next();
				jobList.add(job);
			}
		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		ModelAndView mv = new ModelAndView("viewPostedJob", "jobs", jobList);
		return mv;

	}
	
	@RequestMapping(value = "/viewJobs.htm", method = RequestMethod.GET)
	protected ModelAndView handleViewAppliedJobsRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<ProcessApplications> appList = new ArrayList();
		List appaddList = null;

		String candidate = request.getParameter("email");
		try {
			
			appaddList = employerDAO.listAppliedJob(candidate);
			Iterator appIterator = appaddList.iterator();
			while (appIterator.hasNext()) {

				ProcessApplications processApp = (ProcessApplications) appIterator.next();

				appList.add(processApp);

			}

		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		ModelAndView mv = new ModelAndView("viewCandidateApplications", "apps", appList);
		return mv;
	}

}
