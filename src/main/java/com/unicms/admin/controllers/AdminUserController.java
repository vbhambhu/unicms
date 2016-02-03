package com.unicms.admin.controllers;

import java.util.HashMap;

import javax.validation.Valid;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import groovyjarjarantlr.collections.List;

@Controller
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/admin/users", method=RequestMethod.GET)
    public String showUser(User user, Model model) {
		
		model.addAttribute("users", userService.findAll());
		
        return "admin/users/list";
    }
	
	
	@RequestMapping(value="/admin/users/new", method=RequestMethod.GET)
    public String addUser(User user, Model model) {
		
		HashMap<String, String> roles = new HashMap<String, String>();
		roles.put("1", "SuperAdmin");
		roles.put("2", "Editior");
		model.addAttribute("roles", roles);
		
        return "admin/users/add";
    }
	
	

	 
	@RequestMapping(value="/admin/users/new", method=RequestMethod.POST)
	public String insertUser(@Valid User user, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			HashMap<String, String> roles = new HashMap<String, String>();
			roles.put("1", "SuperAdmin");
			roles.put("2", "Editior");
			model.addAttribute("roles", roles);
			
			 return "admin/users/add";
        }
		
		
		
		
		//System.out.println(categories);
		
		
		//String fileName = image.getOriginalFilename();

//		SecureRandom random = new SecureRandom();
//		String imageName = new BigInteger(130, random).toString(32)+".jpg";
//		postJDBCTemplate.create(post.getTitle(), post.getName(), post.getContent(), imageName , post.getStatus());
        return "redirect:/admin/posts";
    }
	
	/*
	@RequestMapping(value="/admin/posts/edit", method=RequestMethod.GET)
    public String editForm(@RequestParam("id") int id, Model model) {
	 Post post = postJDBCTemplate.getPost( id );
	 model.addAttribute("post", post); 
        return "admin/posts/edit";
    }
	
	@RequestMapping(value="/admin/posts/edit", method=RequestMethod.POST)
	public String editPost(@RequestParam("id") int id,
			@RequestParam("title") String title, 
			@RequestParam("content") String content) {

		//postJDBCTemplate.update(id, title, content);
        return "redirect:/admin/posts";
    }
	
	
	@RequestMapping(value="/admin/posts/delete", method=RequestMethod.GET)
    public String deletePost(@RequestParam("id") int id) {
	
		postJDBCTemplate.delete( id );
	 
		 return "redirect:/admin/posts";
    }    return "admin/login";
	 }
	 */
}
