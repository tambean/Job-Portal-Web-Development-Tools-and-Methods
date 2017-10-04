package com.myneu.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myneu.dao.EmployerDAO;
import com.myneu.dao.JobsDAO;
import com.myneu.pojo.Education;
import com.myneu.pojo.Experience;
import com.myneu.pojo.Jobs;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Controller
public class EmployerPostedJobUpdateController {

	AddJobsValidator addJobvalidator = new AddJobsValidator();
	JobsDAO jobDAO = new JobsDAO();

	@Autowired
	EmployerDAO employerDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(addJobvalidator);
	}
	
	@RequestMapping(value = "/employerupdatePostedJob.htm", method = RequestMethod.GET)
	public ModelAndView handleUpdateEmpJobRequest(@ModelAttribute("job") Jobs job, BindingResult result,HttpServletRequest req) throws AdException {
		
		String jobId = req.getParameter("Id");
		int jobID = Integer.parseInt(jobId.trim());
		String candidateEmail = req.getParameter("email");
		Jobs j = null;
		ModelAndView mv = new ModelAndView();
		try {		
			System.out.println("----------+j.getEmployerName()");
			j  = jobDAO.getJob(jobID);	
			System.out.println("----------"+j.getEmployerName());
		
		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		mv.addObject("jobID",j.getJobID());
		mv.addObject("experienceRequired", j.getExperienceRequired());
		mv.addObject("jobDescription",j.getJobDescription());
		mv.addObject("jobTitle", j.getJobTitle());
		mv.addObject("location",j.getLocation());
		mv.addObject("postedOn",j.getPostedOn());
		mv.addObject("postedBy",j.getPostedBy());
		mv.addObject("jobType", j.getJobType());
		mv.addObject("qualificationRequired",j.getQualificationRequired());
		mv.addObject("skillsRequired",j.getSkillsRequired());
		mv.setViewName("employerJobUpdate");
		return mv;
	}
	
	@RequestMapping(value = "/viewEmployerUpdatedjob.htm", method = RequestMethod.POST)
	public ModelAndView handleUpdatedJobRequest(@ModelAttribute("job") Jobs job, BindingResult result,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String candidateEmail = req.getParameter("email");
		System.out.println("----------candidateEmail"+candidateEmail);
		String jobId = req.getParameter("Id");
		int jobID = Integer.parseInt(jobId.trim());
		List<Jobs> jobList = new ArrayList();
		List jobaddList = null;
		ModelAndView mv = new ModelAndView();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String postedOn = dateFormat.format(date);	
		addJobvalidator.validate(job, result);
		if (result.hasErrors()) {
			mv.setViewName("employerJobUpdate");
		}
		
		try {
			jobDAO.updateJob(jobID, job);
			job.setPostedOn(postedOn);
			System.out.println("----------");
			jobaddList = employerDAO.list(candidateEmail);
			Iterator categIterator = jobaddList.iterator();
			while (categIterator.hasNext()) {
				Jobs j = (Jobs) categIterator.next();
				jobList.add(j);
			}

		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----------"+jobList.size());
		mv = new ModelAndView("viewPostedJob", "jobs", jobList);
		return mv;
	}
}
