package com.myneu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myneu.dao.CandidateDAO;
import com.myneu.dao.EducationDAO;
import com.myneu.dao.EmployerDAO;
import com.myneu.dao.ExperienceDAO;
import com.myneu.dao.LoginDAO;
import com.myneu.pojo.Candidate;
import com.myneu.pojo.Education;
import com.myneu.pojo.Experience;
import com.myneu.pojo.ProcessApplications;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;


@Controller
public class CandidateSuccessHomeController {
	
	AddCandidateFormValidator cvalidator = new AddCandidateFormValidator();
	
	List<Education> educationList_tmp = new ArrayList();
	List<Experience> experienceList_tmp = new ArrayList();
	String skill_tmp = null;
	@Autowired
	LoginDAO loginDAO;
	
	@Autowired
	EmployerDAO employerDAO;
	
	@Autowired
	ExperienceDAO experienceDAO;
	
	@Autowired
	EducationDAO educationDAO;

	@Autowired
	CandidateDAO candDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(cvalidator);		
	}
	
//	@InitBinder
//	private void initeduBinder(WebDataBinder binder) {
//		
//		binder.setValidator(addeducationvalidator);
//	}


	@RequestMapping(value = "/homepage.htm", method = RequestMethod.GET)
	public ModelAndView handleRequestHomePage(HttpServletRequest hsr, HttpServletResponse resp) throws Exception {

		ModelAndView mv = new ModelAndView();

		HttpSession session = hsr.getSession();
		String condition = hsr.getParameter("edu");
		String emailID = (String) session.getAttribute("email");
		
		UserAccount ua = loginDAO.getUserAccount(emailID);
		session.setAttribute("user", ua);
		session.setAttribute("email", emailID);
		String type = ua.getType();

		if (type.equals("employer")) {
			mv.setViewName("loginSuccessEmployer");
		} else if (type.equals("admin")) {
			mv.setViewName("adminHomePage");

		} else {
			if(condition != null){
				mv.addObject("condition", condition);
			}		
			mv.setViewName("loginSuccessCandidate");
		}
		return mv;
	}
	
	@RequestMapping(value = "/appliedjobcandidate.htm", method = RequestMethod.GET)
	public ModelAndView handleRequestJobApply(HttpServletRequest hsr, HttpServletResponse resp) throws Exception {
		List<ProcessApplications> appList = new ArrayList();
		List appaddList = null;
		String employer = hsr.getParameter("email");
		System.out.println("handleRequestJobApply" + employer);
		try {

			appaddList = employerDAO.jobApplication(employer);
			Iterator appIterator = appaddList.iterator();
			while (appIterator.hasNext()) {

				ProcessApplications jobApply = (ProcessApplications) appIterator.next();

				appList.add(jobApply);

			}

		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		ModelAndView mv = new ModelAndView("appliedCandidateJobApplication", "apps", appList);
		return mv;
	}
	
	@RequestMapping(value = "/jobPostSuccess.htm", method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse resp) throws Exception {

		UserAccount ua = (UserAccount) hsr.getSession().getAttribute("user");

		Candidate candidate = candDAO.getCandidate(ua);

		ModelAndView mv = new ModelAndView();
		String postedby = hsr.getParameter("candidate");
		String receiver = hsr.getParameter("postedby");
		String companyName = hsr.getParameter("employerName");
		int jobID = Integer.parseInt(hsr.getParameter("jobID"));
		candDAO.applyJobRequest(postedby, receiver, candidate, companyName, jobID);
		mv.setViewName("applyJobSuccess");
		return mv;
	}
	
	@RequestMapping(value = "/viewMyProfile.htm", method = RequestMethod.GET)
	public ModelAndView handleMyProfileRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String candidateEmail = req.getParameter("email");
		List<Education> educationList = new ArrayList();
		List educationaddList = null;

		List<Experience> experienceList = new ArrayList();
		List experienceaddList = null;
		String skill = null;
		ModelAndView mv = new ModelAndView();

		try {

			UserAccount ua = loginDAO.getUserAccount(candidateEmail);
			skill = candDAO.getCandidateSkills(ua);
			educationaddList = educationDAO.listEducation(candidateEmail);
			experienceaddList = experienceDAO.listExperience(candidateEmail);
			Iterator eduIterator = educationaddList.iterator();

			while (eduIterator.hasNext()) {
				Education education = (Education) eduIterator.next();
				educationList.add(education);
				}

			Iterator expIterator = experienceaddList.iterator();
			while (expIterator.hasNext()) {

				Experience exp = (Experience) expIterator.next();
				experienceList.add(exp);				
			}
		} catch (AdException e) {
			System.out.println(e.getMessage());
		}

		mv.addObject("skill",skill);
		mv.addObject("education", educationList);
		mv.addObject("experience", experienceList);
		mv.setViewName("candidateViewUpdateProfile");
		return mv;
	}
	
	@RequestMapping(value = "/candidateupdateskill.htm", method = RequestMethod.GET)
	public ModelAndView handleUpdateSkillRequest(@ModelAttribute("can")Candidate can,BindingResult result ,
										HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String candidateEmail = req.getParameter("email");
		String skill = null;
		ModelAndView mv = new ModelAndView();
		try {

			UserAccount ua = loginDAO.getUserAccount(candidateEmail);
			skill = candDAO.getCandidateSkills(ua);
		
		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		mv.addObject("skills",skill);
		mv.setViewName("candidateskillUpdateProfile");
		return mv;
	}
	
	
	
	@RequestMapping(value = "/viewUpdatedMySkillProfile.htm", method = RequestMethod.POST)
	public ModelAndView handleUpdateProfileRequest(@ModelAttribute("cand") Candidate can, BindingResult result,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String candidateEmail = req.getParameter("email");
		List<Education> educationList = new ArrayList();
		List educationaddList = null;

		List<Experience> experienceList = new ArrayList();
		List experienceaddList = null;
		String skill = null;
		ModelAndView mv = new ModelAndView();

		try {

			UserAccount ua = loginDAO.getUserAccount(candidateEmail);
			candDAO.updateCandidateSkills(ua, can.getSkill());
			skill = can.getSkill();
			educationaddList = educationDAO.listEducation(candidateEmail);
			experienceaddList = experienceDAO.listExperience(candidateEmail);
			Iterator eduIterator = educationaddList.iterator();

			while (eduIterator.hasNext()) {
				Education education = (Education) eduIterator.next();
				educationList.add(education);				
			}

			Iterator expIterator = experienceaddList.iterator();
			while (expIterator.hasNext()) {

				Experience exp = (Experience) expIterator.next();
				experienceList.add(exp);
			}
		} catch (AdException e) {
			System.out.println(e.getMessage());
		}

		mv.addObject("skill",skill);
		mv.addObject("education", educationList);
		mv.addObject("experience", experienceList);
		mv.setViewName("candidateViewUpdateProfile");
		return mv;
	}
	
	
}
