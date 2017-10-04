package com.myneu.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myneu.dao.LoginDAO;
import com.myneu.pojo.Employer;
import com.yusuf.spring.exception.AdException;

public class AddEmployerFormValidator implements Validator {

	private String emailPattern = "^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$*()%]).{6,20})";
	private String alphastart = "^[a-zA-Z]+[0-9a-zA-Z\\s]*$";

	public boolean supports(Class aClass) {
		return aClass.equals(Employer.class);
	}

	public void validate(Object obj, Errors errors) {

		Employer emp = (Employer) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.invalid.user", "emailId Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.user", "password Required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
		// "error.invalid.user", "confirmPassword Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employerName", "error.invalid.user",
				"employerName Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "error.invalid.user", "Location Required");

		String emailId = emp.getEmailId();
		String password = emp.getPassword();
		// String cpassword = emp.getConfirmPassword();
		String companyName = emp.getEmployerName();
		String location = emp.getLocation();

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
		if (!(companyName.matches(alphastart))) {

			errors.rejectValue("employerName", "Test",
					"employer Name  hould start with alphabet followed by alphanumeric characters");
		}
		if (!(location.matches(alphastart))) {

			errors.rejectValue("location", "Test",
					"Location should start with alphabet followed by alphanumeric characters");
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
