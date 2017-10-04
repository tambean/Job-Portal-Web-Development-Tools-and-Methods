package com.myneu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myneu.dao.LoginDAO;
import com.myneu.pojo.UserAccount;

@Controller
@RequestMapping("/login.htm")
public class LoginController {

	
	@Autowired
	LoginDAO loginDAO;
	 
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
        HttpSession session = hsr.getSession();
        String action = hsr.getParameter("action");
        ModelAndView mv = new ModelAndView();
        
        if(action.equals("login")){
        	 String emailID = hsr.getParameter("user");
             String password = hsr.getParameter("password");
                   
             //LoginDAO loginDAO = new LoginDAO();
             UserAccount ua = loginDAO.getUserAccount(emailID, password);
             
             if(ua !=null){
             session.setAttribute("email", emailID);
             session.setAttribute("user", ua);
             String type = ua.getType();
             
             System.out.println("tttyyyyppppeee" +type);
             if(type.equals("employer")){            
             mv.setViewName("loginSuccessEmployer");
             }
             else if(type.equals("admin")){
            mv.setViewName("adminHomePage"); 
            	 
             }
             else{
            	 
            	 mv.setViewName("loginSuccessCandidate");
             }
             } 
             else{
                 mv.addObject("invalidCredentials", "true");
                 
                 mv.setViewName("home");
             }
             
        }
        
		return mv;
	}
	
	@RequestMapping("/logout.htm")
	public String logoutUser(HttpServletRequest hsr){
		
		HttpSession session = hsr.getSession();
		 String action = hsr.getParameter("action");
		 String view = null;
		 
		if (action.equals("logout")){
        	session.invalidate();
        	view =  "home";
        	
        }
		return view;
	}
	
}
