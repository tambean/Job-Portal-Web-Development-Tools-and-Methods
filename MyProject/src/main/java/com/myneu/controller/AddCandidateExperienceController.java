package com.myneu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myneu.controller.AddExperienceValidator;
import com.myneu.dao.CandidateDAO;
import com.myneu.dao.ExperienceDAO;
import com.myneu.pojo.Candidate;
import com.myneu.pojo.Experience;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Controller
@RequestMapping("/addExperience.htm")
public class AddCandidateExperienceController {
	
	
	AddExperienceValidator addexperiencevalidator = new AddExperienceValidator();

	@Autowired
	ExperienceDAO experienceDAO;
	
	@Autowired
	CandidateDAO candidateDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(addexperiencevalidator);
	}
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("experience") Experience experience, BindingResult result,  HttpServletRequest hsr) throws Exception {
		addexperiencevalidator.validate(experience, result);
		if (result.hasErrors()) {
			return "addCandidateExperience";
		}
		
		UserAccount ua = (UserAccount) hsr.getSession().getAttribute("user");
		

		try {
		
			Candidate candidate = candidateDAO.getCandidate(ua);
			
			//ExperienceDAO experienceDAO = new ExperienceDAO();
			experienceDAO.createexperience(experience.getCompanyName(), experience.getStartDate(), experience.getEndDate(),
					experience.getDesignation(), experience.getResponsibilities(), hsr.getParameter("email"), candidate);
					
			
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "addCandidateExperienceSuccess";
	}



	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("experience") Experience experience, BindingResult result) {

		return "addCandidateExperience";
	}

}
