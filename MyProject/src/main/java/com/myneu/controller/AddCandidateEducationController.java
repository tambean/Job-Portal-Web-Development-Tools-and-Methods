package com.myneu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myneu.controller.AddEducationValidator;
import com.myneu.dao.CandidateDAO;
import com.myneu.dao.EducationDAO;
import com.myneu.dao.LoginDAO;
import com.myneu.pojo.Candidate;
import com.myneu.pojo.Education;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Controller
@RequestMapping("/addEducation.htm")
public class AddCandidateEducationController {
	
	
	AddEducationValidator addeducationvalidator = new AddEducationValidator();

	@Autowired
	EducationDAO educationDAO;
	
	//EducationDAO educationDAO = new EducationDAO();
	
	@Autowired
	CandidateDAO candidateDAO;
	
	@Autowired
	LoginDAO loginDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(addeducationvalidator);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("education") Education education, BindingResult result, HttpServletRequest hsr) throws Exception {
		addeducationvalidator.validate(education, result);
		if (result.hasErrors()) {
			return "addCandidateEducation";
		}	
				
		try {
			HttpSession session = hsr.getSession();	
			String email = hsr.getParameter("email");
			UserAccount ua = loginDAO.getUserAccount(email);
			System.out.println("Added" +email);
				
			Candidate candidate = candidateDAO.getCandidate(ua);		
			System.out.println("getCandidate");
			
			educationDAO.createeducation(education.getGradattendedFromDate(), 
					education.getGradattendedToDate(),education.getGradDegreeName(),
					education.getSchoolattendedFromDate(),education.getSchoolattendedToDate(),
					education.getSchoolName(),education.getUnderGradattendedFromDate(),
					education.getUnderGradattendedToDate(),education.getUnderGradDegreeName(),
					email, candidate);
			loginDAO.UpdateCandidateUserEdu(email);	
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "addCandidateEducationSuccess";
	}



	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("education") Education education, BindingResult result) {

		return "addCandidateEducation";
	}

}
