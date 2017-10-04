package com.myneu.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myneu.pojo.Education;

public class AddEducationValidator implements Validator{
	
	private String alphastart = "^[a-zA-Z]+[0-9a-zA-Z\\s]*$";
	public boolean supports(Class aClass)
    {
        return aClass.equals(Education.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Education education = (Education) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "underGradDegreeName", "error.invalid.user", "underGradDegreeName Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "underGradattendedFromDate", "error.invalid.user", "underGradattendedFromDate Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "underGradattendedToDate", "error.invalid.user", "underGradattendedToDate Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "schoolName", "error.invalid.user", "schoolName Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "schoolattendedFromDate", "error.invalid.user", "schoolattendedFromDate Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "schoolattendedToDate", "error.invalid.user", "schoolattendedToDate Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gradDegreeName", "error.invalid.user", "Write NA for no grad degree");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gradattendedFromDate", "error.invalid.user", "leave as it for no gradattendedFromDate");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gradattendedToDate", "error.invalid.user", "leave as it for no gradattendedToDate");
        
        String schoolName = education.getSchoolName();
        String underGradDegreeName = education.getUnderGradDegreeName();
        String gradDegreeName = education.getGradDegreeName();
    
    
    
    
        if(!(schoolName.matches(alphastart))){
	    	  
	    	  errors.rejectValue("schoolName", "Test", "School Name should start with alphabet followed by alphanumeric characters");
	      }
	      if(!(underGradDegreeName.matches(alphastart))){
	  
	  errors.rejectValue("underGradDegreeName", "Test", "underGradDegreeName should start with alphabet followed by alphanumeric characters");
	      }
	      if(!(gradDegreeName.matches(alphastart))){
	    	  
	    	  errors.rejectValue("gradDegreeName", "Test", "Degree Name should start with alphabet followed by alphanumeric characters");
	    	      }
    }
    
}
