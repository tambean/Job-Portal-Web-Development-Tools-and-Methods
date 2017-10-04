package com.myneu.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myneu.pojo.Jobs;

public class AddJobsValidator implements Validator {

	private String pattern = "^[a-zA-Z]+[0-9a-zA-Z\\s]*$";
	private String exppattern = "^(0?[1-9]|[1-9][0-9])$";
	private String phonepattern = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
	
	public boolean supports(Class aClass)
    {
        return aClass.equals(Jobs.class);
    }
	
    public void validate(Object obj, Errors errors)
    {
        Jobs job = (Jobs) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobTitle", "error.invalid.user", "job title Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobDescription", "error.invalid.user", "job description Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobType", "error.invalid.user", "job type Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "experienceRequired", "error.invalid.user", "experience Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "skillsRequired", "error.invalid.user", "skilss Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "qualificationRequired", "error.invalid.user", "qualification Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "error.invalid.user", "location Required");
       
        
        String jobtitle = job.getJobTitle();
        String jobDescription = job.getJobDescription();
        String jobType = job.getJobType();
        String experience = String.valueOf(job.getExperienceRequired());
        String skill = job.getSkillsRequired();
        String qualification = job.getQualificationRequired();
        String location = job.getLocation();
              
        if(!(jobtitle.matches(pattern))) {
      	  errors.rejectValue("jobTitle", "Test", "Should start with alphabet");
      }
        if(!(jobDescription.matches(pattern))) {
        	  errors.rejectValue("jobDescription", "Test", "Should start with alphabet");
        }
        if(!(jobType.matches(pattern))) {
        	  errors.rejectValue("jobType", "Test", "Should start with alphabet");
        }
        if(!(experience.matches(exppattern))) {
        	  errors.rejectValue("experience", "Test", "Must be 2 digits");
        }
        if(!(qualification.matches(pattern))) {
      	  errors.rejectValue("qualification", "Test", "Should start with alphabet");
      }
       
        if(!(location.matches(pattern))) {
        	  errors.rejectValue("location", "Test", "Should start with alphabet");
        }
          
     }
}
