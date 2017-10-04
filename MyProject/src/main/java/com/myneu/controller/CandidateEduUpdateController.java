package com.myneu.controller;

import java.util.ArrayList;
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

import com.myneu.dao.CandidateDAO;
import com.myneu.dao.EducationDAO;
import com.myneu.dao.EmployerDAO;
import com.myneu.dao.ExperienceDAO;
import com.myneu.dao.LoginDAO;
import com.myneu.pojo.Education;
import com.myneu.pojo.Experience;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Controller
public class CandidateEduUpdateController {
	
	AddEducationValidator addeducationvalidator = new AddEducationValidator();
	
	@Autowired
	LoginDAO loginDAO;
	
	@Autowired
	ExperienceDAO experienceDAO;
	
	@Autowired
	EducationDAO educationDAO;

	@Autowired
	CandidateDAO candDAO;
	
	@InitBinder
	private void initeduBinder(WebDataBinder binder) {

		binder.setValidator(addeducationvalidator);
	}
	
	
	@RequestMapping(value = "/candidateupdateeducation.htm", method = RequestMethod.GET)
	public ModelAndView handleUpdateEducationRequest(@ModelAttribute("education")Education education,BindingResult result ,
										HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String candidateEmail = req.getParameter("email");
		Education edu = null;
		ModelAndView mv = new ModelAndView();
		try {			
			edu  = educationDAO.getEducation(candidateEmail);			
		
		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		mv.addObject("schoolNam", edu.getSchoolName());
		mv.addObject("schoolattendedFromDat", edu.getSchoolattendedFromDate());
		mv.addObject("schoolattendedToDat", edu.getSchoolattendedToDate());
		mv.addObject("underGradDegreeNam", edu.getUnderGradDegreeName());
		mv.addObject("underGradattendedFromDat", edu.getUnderGradattendedFromDate());
		mv.addObject("underGradattendedToDat", edu.getUnderGradattendedToDate());
		mv.addObject("gradDegreeNam", edu.getGradDegreeName());
		mv.addObject("gradattendedFromDat", edu.getGradattendedFromDate());
		mv.addObject("gradattendedToDat", edu.getGradattendedToDate());
		mv.setViewName("candidateeducationUpdateProfile");
		return mv;
	}
	
	@RequestMapping(value = "/viewUpdatedMyEducationProfile.htm", method = RequestMethod.POST)
	public ModelAndView handleUpdatedEduProfileRequest(@ModelAttribute("education") Education education, BindingResult result,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String candidateEmail = req.getParameter("email");
		List<Education> educationList = new ArrayList();
		List<Experience> experienceList = new ArrayList();
		List experienceaddList = null;
		String skill = null;
		ModelAndView mv = new ModelAndView();
		System.out.println("--------"+education.getSchoolName());
		try {
			UserAccount ua = loginDAO.getUserAccount(candidateEmail);
			int id = ua.getUserId();
			skill = candDAO.getCandidateSkills(ua);
			educationDAO.UpdateEducation(id,education);
			experienceaddList = experienceDAO.listExperience(candidateEmail);
			System.out.println("--Before---"+education.getSchoolName());
			educationList.add(education);
			System.out.println("--After---"+education.getSchoolName());
			Iterator expIterator = experienceaddList.iterator();
			while (expIterator.hasNext()) {

				Experience exp = (Experience) expIterator.next();
				experienceList.add(exp);				
			}
			System.out.println("----------");

		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		mv.clear();
		mv.addObject("skill",skill);
		mv.addObject("education", educationList);
		mv.addObject("experience", experienceList);
		mv.setViewName("candidateViewUpdateProfile");		
		return mv;
	}

}
