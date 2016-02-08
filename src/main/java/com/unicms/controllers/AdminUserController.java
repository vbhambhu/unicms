package com.unicms.controllers;

import java.util.HashMap;

import javax.validation.Valid;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unicms.models.User;
import com.unicms.services.UserService;

import groovyjarjarantlr.collections.List;

@Controller
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/admin/user", method=RequestMethod.GET)
    public String showUser(User user, Model model) {
		model.addAttribute("users", userService.listUser());
        return "admin/users/list";
    }
	
	
	@RequestMapping(value="/admin/user/new", method=RequestMethod.GET)
    public String addUser(User user, Model model) {
		
		model.addAttribute("roles", userService.listRoles());
        return "admin/users/add";
    }
	
	

	 
	@RequestMapping(value="/admin/user/new", method=RequestMethod.POST)
	public String insertUser(@Valid User user, BindingResult bindingResult, Model model) {
		
		model.addAttribute("roles", userService.listRoles());
		
		if (bindingResult.hasErrors()) {
			return "admin/users/add";
        }
		
		if( userService.emailExists( user.getEmail() ) ){
			bindingResult.rejectValue("email", "error.user", "An account already exists for this email.");
		}
		
		if( userService.usernameExists( user.getUsername() ) ){
			bindingResult.rejectValue("username", "error.user", "An account already exists with this username.");
		}
		
		if (bindingResult.hasErrors()) {
			return "admin/users/add";
        }
		
		
		
		//Create new user
		userService.createUser(user);
		
		return "redirect:/admin/user";
    }
	
	
	@RequestMapping(value="/admin/user/edit", method=RequestMethod.GET)
	public String editPost(@RequestParam("id") int id, Model model) {
		
		User user = userService.getUserById( id );
		
		model.addAttribute("user", user); 
		
		model.addAttribute("roles", userService.listRoles());
		
		return "admin/users/edit";
    }
	
	/*
	@RequestMapping(value="/admin/posts/edit", method=RequestMethod.GET)
    public String editForm(@RequestParam("id") int id, Model model) {
	 Post post = postJDBCTemplate.getPost( id );
	 model.addAttribute("post", post); 
        return "admin/posts/edit";
    }
	
	
	
	
	@RequestMapping(value="/admin/posts/delete", method=RequestMethod.GET)
    public String deletePost(@RequestParam("id") int id) {
	
		postJDBCTemplate.delete( id );
	 
		 return "redirect:/admin/posts";
    }    return "admin/login";
	 }
	 */
}
