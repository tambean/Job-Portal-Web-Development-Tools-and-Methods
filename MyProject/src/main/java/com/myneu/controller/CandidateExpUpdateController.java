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
import com.myneu.dao.ExperienceDAO;
import com.myneu.dao.LoginDAO;
import com.myneu.pojo.Education;
import com.myneu.pojo.Experience;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Controller
public class CandidateExpUpdateController {
	
	AddExperienceValidator addexperiencevalidator = new AddExperienceValidator();

	@Autowired
	LoginDAO loginDAO;
	
	@Autowired
	ExperienceDAO experienceDAO;
	
	@Autowired
	EducationDAO educationDAO;

	@Autowired
	CandidateDAO candDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(addexperiencevalidator);
	}
	
	@RequestMapping(value = "/candidateupdateexperience.htm", method = RequestMethod.GET)
	public ModelAndView handleUpdateExperienceRequest(@ModelAttribute("experience")Experience experience,BindingResult result ,
										HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String candidateEmail = req.getParameter("email");
		String expID = req.getParameter("Id");
		Experience exp = null;
		ModelAndView mv = new ModelAndView();
		try {

			UserAccount ua = loginDAO.getUserAccount(candidateEmail);
			exp  = experienceDAO.getExperience(candidateEmail,expID);			
		
		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		mv.addObject("expID",expID);
		mv.addObject("companyName", exp.getCompanyName());
		mv.addObject("startDate", exp.getStartDate());
		mv.addObject("endDate", exp.getEndDate());
		mv.addObject("designation", exp.getDesignation());
		mv.addObject("responsibilities", exp.getResponsibilities());	
		mv.setViewName("candidateexperienceUpdateProfile");
		return mv;
	}
	
	@RequestMapping(value = "/viewUpdatedMyExperienceProfile.htm", method = RequestMethod.POST)
	public ModelAndView handleUpdatedExpProfileRequest(@ModelAttribute("experience") Experience experience, BindingResult result,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String candidateEmail = req.getParameter("email");
		String expID = req.getParameter("Id");
		List<Education> educationList = new ArrayList();
		List educationaddList = null;

		List<Experience> experienceList = new ArrayList();
		List experienceaddList = null;
		String skill = null;
		ModelAndView mv = new ModelAndView();
		
		try {
			UserAccount ua = loginDAO.getUserAccount(candidateEmail);
			int id = Integer.parseInt(expID.trim());
			skill = candDAO.getCandidateSkills(ua);
			experienceDAO.UpdateExperience(id,experience);
			experienceaddList = experienceDAO.listExperience(candidateEmail);
			educationaddList = educationDAO.listEducation(candidateEmail);
		
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
