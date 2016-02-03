package com.unicms.admin.controllers;

import javax.validation.Valid;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import groovyjarjarantlr.collections.List;

@Controller
public class AdminController {
	
	
	 @RequestMapping(value="/admin", method=RequestMethod.GET)
	    public String showDashboard() {
	        return "admin/dashboard";
	 }
	 
	 
	 @RequestMapping(value="/admin/login", method=RequestMethod.GET)
	 public String showLogin(User user) {
	        return "admin/login";
	 }
	 
	 
	 @RequestMapping(value="/admin/loginsss", method=RequestMethod.POST)
	 public String checkPersonInfo(@Valid User user, BindingResult bindingResult) {

	        if (bindingResult.hasErrors()) {
	        	
	        	
	        	 System.out.println("==========Yee had error =========");
	        	
	        	
	        	for (Object object : bindingResult.getAllErrors()) {
	        	    if(object instanceof FieldError) {
	        	        FieldError fieldError = (FieldError) object;

	        	        System.out.println(fieldError.getCode());
	        	    }

	        	  
	        	}
	           
	            
	            
	            return "admin/login";
	        }
	        
	        

	       return "redirect:/results";
	    }
	 
	 
	 

}
