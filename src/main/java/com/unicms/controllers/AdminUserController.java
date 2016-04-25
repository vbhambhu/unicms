package com.unicms.controllers;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unicms.models.User;
import com.unicms.services.UserService;

import groovyjarjarantlr.collections.List;

@Controller
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/admin/user", method=RequestMethod.GET)
    public String showUser(
    		User user, 
    		Model model,
    		HttpServletRequest request,
    		@RequestParam(value = "page", defaultValue = "1") Integer page , 
    		@RequestParam(value = "limit", defaultValue = "5") Integer perPage,
    		@RequestParam(value = "sort", defaultValue = "created_at") String sortBy,
    		@RequestParam(value = "dir", defaultValue = "asc") String order,
    		@RequestParam(value = "q", defaultValue = "") String query
    		) {
		
		int start = (page==null) ? 0 : (page-1) * perPage;
		int total_rows = userService.count(query);
		int total_pages = (int) Math.ceil(total_rows / (float)perPage);
		int pStart = (page - 2) > 0  ? (page - 2) : 1;
		int pEnd = (page + 2) < total_pages  ? (page + 2) : total_pages;
		
		
		model.addAttribute("users", userService.listUser(perPage, start, sortBy, order, query));
		model.addAttribute("currentPage", page);
		model.addAttribute("nextPage", page + 1);
		model.addAttribute("lastPage", total_pages);
		model.addAttribute("nxtdir", order.equals("asc") ? "desc" : "asc");
		model.addAttribute("pStart", pStart);
		model.addAttribute("pEnd", pEnd);
		
		  
        return "admin/users/list";
        
    }
	
	
	@RequestMapping(value="/admin/user/new", method=RequestMethod.GET)
    public String insertUser(User user, Model model) {
		
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
			bindingResult.rejectValue("email", "error.user", "An account already exists for this email address.");
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
	public String editUser(@RequestParam("id") int id, Model model) {
		
		User user = userService.getUserById( id );
		model.addAttribute("user", user); 
		model.addAttribute("roles", userService.listRoles());
		
		return "admin/users/edit";
		
    }
	
	
	@RequestMapping(value="/admin/user/edit", method=RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult bindingResult, @RequestParam("id") int id, Model model) {
		
		
		model.addAttribute("user", user); 
		model.addAttribute("roles", userService.listRoles());
		
		if (bindingResult.hasErrors()) {
			return "admin/users/edit";
        }
		
		if( userService.emailCountByEmail( user.getEmail() ) > 1){
			bindingResult.rejectValue("email", "error.user", "An account already exists for this email address.");
		}
		
		if( userService.usernameCountByUsername( user.getUsername() ) > 1 ){
			bindingResult.rejectValue("username", "error.user", "An account already exists with this username.");
		}
		
		if (bindingResult.hasErrors()) {
			return "admin/users/edit";
        }
		
		
		userService.updateUser(user);
		
		return "redirect:/admin/user";
		
		
		
		
		/*
		model.addAttribute("user", user); 
		model.addAttribute("roles", userService.listRoles());
		
		
		
		
		if( userService.emailExists( user.getEmail() ) ){
			bindingResult.rejectValue("email", "error.user", "An account already exists for this email.");
		}
		
		if( userService.usernameExists( user.getUsername() ) ){
			bindingResult.rejectValue("username", "error.user", "An account already exists with this username.");
		}
		
		if (bindingResult.hasErrors()) {
			return "admin/users/add";
        }
		
		
		return "admin/users/edit";
		
		*/
    }
	
	@RequestMapping(value="/admin/user/delete", method=RequestMethod.GET)
	public String deleteUser(@RequestParam("id") int id, Model model) {
		
		User user = userService.getUserById(id);
		model.addAttribute("user", user); 
		return "admin/users/delete";
		
    }
	
	@RequestMapping(value="/admin/user/delete", method=RequestMethod.POST)
    public String deleteUser(User user) {
		
		userService.deleteUser(user.getId());
		return "redirect:/admin/user";
		
	}
	
	
	@RequestMapping(value="/admin/user/validate", method=RequestMethod.GET)
	@ResponseBody
	public Map validateUser(
			@RequestParam(value = "o", defaultValue = "") String column,
			@RequestParam(value = "n", defaultValue = "") String newcolumn 
			) {
		
		Map<String,Boolean> map = new HashMap<>();
		
		map.put("isValid", false);
		
		
		if(column.trim().length() > 0 && newcolumn.trim().length() > 0 && column.equals("username")){
		
			Boolean validUsername = (userService.usernameExists(newcolumn)) ? false : true;
			map.put("isValid", validUsername);
			
		} else if(column.trim().length() > 0 && newcolumn.trim().length() > 0 && column == "email"){
			
			System.out.println("i m email");
			Boolean validEmail = (userService.emailExists(newcolumn)) ? false : true;
			map.put("isValid", validEmail);
			
		}
		
		return map;
		
	}
	
}
