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

import com.myneu.dao.CandidateDAO;
import com.myneu.dao.EducationDAO;
import com.myneu.dao.ExperienceDAO;
import com.myneu.dao.LoginDAO;
import com.myneu.pojo.Education;
import com.myneu.pojo.Experience;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Controller
public class ViewCandidateApplicationController {

	@Autowired
	EducationDAO educationDAO;
	
	@Autowired
	CandidateDAO candidateDAO;

	@Autowired
	ExperienceDAO experienceDAO;
	
	@Autowired
	LoginDAO loginDAO;

	@RequestMapping(value = "/viewcandidateprofile.htm", method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse resp) throws Exception {
		List<Education> educationList = new ArrayList();
		List educationaddList = null;

		List<Experience> experienceList = new ArrayList();
		List experienceaddList = null;
		String skill = null;

		String candidateEmail = hsr.getParameter("action");
		try {
			
			UserAccount ua = loginDAO.getUserAccount(candidateEmail);
			skill = candidateDAO.getCandidateSkills(ua);
			educationaddList = educationDAO.listEducation(candidateEmail);
			experienceaddList = experienceDAO.listExperience(candidateEmail);
			Iterator eduIterator = educationaddList.iterator();

			while (eduIterator.hasNext()) {
				Education education = (Education) eduIterator.next();
				educationList.add(education);
			}

			Iterator expIterator = experienceaddList.iterator();
			while (expIterator.hasNext()) {

				Experience professionalEx = (Experience) expIterator.next();
				experienceList.add(professionalEx);
			}
		} catch (AdException e) {
			System.out.println(e.getMessage());
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("skill",skill);
		mv.addObject("education", educationList);
		mv.addObject("experience", experienceList);
		mv.setViewName("viewCandidateProfile");
		return mv;
	}
}
