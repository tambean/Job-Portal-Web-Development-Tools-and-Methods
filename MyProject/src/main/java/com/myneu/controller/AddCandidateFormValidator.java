package com.myneu.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myneu.dao.LoginDAO;
import com.myneu.pojo.Candidate;
import com.yusuf.spring.exception.AdException;

public class AddCandidateFormValidator implements Validator {

	private String emailPattern = "^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$*()%]).{6,20})";
	private String alphastart = "^[a-zA-Z]+[0-9a-zA-Z\\s]*$";
	// private static final String RESUME_PATTERN =
	// "([^\\s]+(\\.(?i)(pdf|doc))$)";
	private String phonepattern = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";

	public boolean supports(Class aClass) {
		return aClass.equals(Candidate.class);
	}

	public void validate(Object obj, Errors errors) {
		Candidate candidate = (Candidate) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.invalid.user", "emailId Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.user", "password Required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
		// "error.invalid.user", "confirmPassword Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "FirstName Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "LastName Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "error.invalid.user", "Phone Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "skill", "error.invalid.user", "Skill Required");

		String emailId = candidate.getEmailId();
		String password = candidate.getPassword();
		// String cpassword = candidate.getConfirmPassword();
		String firstName = candidate.getFirstName();
		String lastName = candidate.getLastName();
		String phone = candidate.getPhone();
		String skill = candidate.getSkill();

		if (!(emailId.matches(emailPattern))) {
			errors.rejectValue("emailId", "Test", "Enter email ID with correct format");
		}
		if (!(password.matches(passwordPattern))) {

			errors.rejectValue("password", "Test", "Password should contain alphanumeric characters");
		}
		// if(!(cpassword.matches(passwordPattern))){
		//
		// errors.rejectValue("confirmPassword", "Test", "Password should
		// contain alphanumeric characters");
		// }
		if (!(firstName.matches(alphastart))) {

			errors.rejectValue("firstName", "Test", "first NAme should start with alphabet");
		}
		if (!(lastName.matches(alphastart))) {

			errors.rejectValue("lastName", "Test", "last NAme should start with alphabet");
		}

		if (!(skill.matches(alphastart))) {

			errors.rejectValue("skill", "Test", "skill should start with alphabet");
		}

		if (!(phone.matches(phonepattern))) {

			errors.rejectValue("phone", "Test", "should be in [3digits]-[3digits]-[4digits]");
		}
		LoginDAO loginDAO = new LoginDAO();
		Boolean present;
		try {
			present = loginDAO.checkIfEmailExists(emailId);

			if (present == true) {
				errors.rejectValue("emailId", "error.invalid.user",
						"Email already in use.Please enter different mail.");
			}
		} catch (AdException e) {
			e.printStackTrace();
		}

	}

}
