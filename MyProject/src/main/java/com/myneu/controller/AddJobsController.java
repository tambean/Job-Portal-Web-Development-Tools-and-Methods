package com.myneu.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myneu.controller.AddJobsValidator;
import com.myneu.dao.EmployerDAO;
import com.myneu.dao.JobsDAO;
import com.myneu.pojo.Employer;
import com.myneu.pojo.Jobs;
import com.myneu.pojo.UserAccount;
import com.yusuf.spring.exception.AdException;

@Controller
@RequestMapping("/addjob.htm")
public class AddJobsController {

	AddJobsValidator addJobvalidator = new AddJobsValidator();
	
//	@Autowired
//	JobsDAO jobDAO;
	
	
	JobsDAO jobDAO = new JobsDAO();
	
	@Autowired
	EmployerDAO employerDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(addJobvalidator);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("job") Jobs job, BindingResult result, HttpServletRequest hsr) throws Exception {
		addJobvalidator.validate(job, result);
		if (result.hasErrors()) {
			return "addJob";
		}
		
		UserAccount userAccount = (UserAccount) hsr.getSession().getAttribute("user");
		String postedBy = userAccount.getEmailId(); 

		Employer employer = employerDAO.getEmployer(userAccount);
		try {
					
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			String postedOn = dateFormat.format(date);
			
			System.out.println("postedon"  +postedOn);
			System.out.println("postedBy" +postedBy);
			String employerName = employer.getEmployerName();
			System.out.println("employerName"  +employerName);
			
			jobDAO.createjob(job.getJobTitle(), job.getJobDescription(), 
					job.getJobType(), job.getExperienceRequired(), job.getSkillsRequired(),
					job.getQualificationRequired(), job.getLocation(),
					postedOn, postedBy, employer, employerName );
			
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "addedJob";
	}



	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("job") Jobs job, BindingResult result) {

		return "addJob";
	}
}
