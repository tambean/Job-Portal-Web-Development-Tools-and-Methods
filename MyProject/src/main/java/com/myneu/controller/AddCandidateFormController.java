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

import com.myneu.controller.AddCandidateFormValidator;
import com.myneu.dao.CandidateDAO;
import com.myneu.dao.LoginDAO;
import com.myneu.pojo.Candidate;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Controller
@RequestMapping("/candidateSign.htm")
public class AddCandidateFormController {
	
	
	
	AddCandidateFormValidator cvalidator = new AddCandidateFormValidator();
	@Autowired
	CandidateDAO candidateDAO;
	@Autowired
	LoginDAO loginDAO;
	//CandidateDAO candidateDAO = new CandidateDAO();
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(cvalidator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("candidate") Candidate candidate, BindingResult result) {

		return "addCandidateForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("candidate") Candidate candidate, BindingResult result,HttpServletRequest request) throws AdException {		
		
		
		HttpSession session = request.getSession();
		cvalidator.validate(candidate, result);
		if (result.hasErrors()) {
			return "addCandidateForm";
		}
		
		Candidate c = candidateDAO.create(candidate);
	
		
		UserAccount ua = loginDAO.getUserAccount(c.getEmailId(), c.getPassword());
        
        if(ua !=null){
        session.setAttribute("email", c.getEmailId());
        session.setAttribute("user", ua);		
        }

		return "loginSuccessCandidate";
	}

}
