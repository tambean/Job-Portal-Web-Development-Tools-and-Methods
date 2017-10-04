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


import com.myneu.dao.EmployerDAO;
import com.myneu.dao.LoginDAO;
import com.myneu.pojo.Employer;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;



@Controller
@RequestMapping("/employerSign.htm")
public class AddEmployerFormController {
	
	@Autowired
	EmployerDAO employerDAO;
	@Autowired
	LoginDAO loginDAO;
	
	AddEmployerFormValidator evalidator = new AddEmployerFormValidator();
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(evalidator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("employer") Employer employer, BindingResult result) {

		return "addEmployerForm";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("employer") Employer employer, BindingResult result,HttpServletRequest request) throws AdException {		
		
		
		HttpSession session = request.getSession();
		evalidator.validate(employer, result);
		if (result.hasErrors()) {
			return "addEmployerForm";
		}
		
		Employer e = employerDAO.create(employer);
		
		UserAccount ua = loginDAO.getUserAccount(e.getEmailId(), e.getPassword());

		if (ua != null) {
			session.setAttribute("email", e.getEmailId());
			session.setAttribute("user", ua);
		}

		return "loginSuccessEmployer";
	}

}

