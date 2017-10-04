package com.myneu.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myneu.pojo.Experience;

public class AddExperienceValidator implements Validator {
	
	private String alphastart = "^[a-zA-Z]+[0-9a-zA-Z\\s]*$";
	public boolean supports(Class aClass)
    {
        return aClass.equals(Experience.class);
    }

	public void validate(Object obj, Errors errors) {
		Experience professionalEx = (Experience) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "error.invalid.user", "companyName Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "error.invalid.user", "startDate Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "error.invalid.user", "endDate Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "designation", "error.invalid.user", "designation Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "responsibilities", "error.invalid.user",
				"responsibilities Required");

		String companyName = professionalEx.getCompanyName();
		String designation = professionalEx.getDesignation();
		String responsibilities = professionalEx.getResponsibilities();
		if (!(companyName.matches(alphastart))) {

			errors.rejectValue("companyName", "Test",
					"Company Name should start with alphabet followed by alphanumeric characters");
		}
		if (!(designation.matches(alphastart))) {

			errors.rejectValue("designation", "Test",
					"Designation should start with alphabet followed by alphanumeric characters");
		}
		if (!(responsibilities.matches(alphastart))) {

			errors.rejectValue("responsibilities", "Test",
					"Responsibilities should start with alphabet followed by alphanumeric characters");
		}
	}

}
