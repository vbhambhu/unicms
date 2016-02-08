package com.unicms.controllers;

import javax.validation.Valid;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unicms.services.PostService;

import groovyjarjarantlr.collections.List;

@Controller
public class AdminBlogController {
	
	@Autowired
	PostService postService;
	
	public String showPosts(Model model) {
		model.addAttribute("posts", postService.findAll() );
        return "admin/posts/list";
    }
	 
	/*
	 
	@RequestMapping(value="/admin/posts/new", method=RequestMethod.POST)
	public String insertPost(@Valid Post post, BindingResult bindingResult,
			@RequestParam("image") MultipartFile image,
			@RequestParam("categories") List categories) {
		
		if (bindingResult.hasErrors()) {
			 return "admin/posts/insert";
        }
		
		System.out.println(categories);
		
		
		//String fileName = image.getOriginalFilename();

		SecureRandom random = new SecureRandom();
		String imageName = new BigInteger(130, random).toString(32)+".jpg";
		postJDBCTemplate.create(post.getTitle(), post.getName(), post.getContent(), imageName , post.getStatus());
        return "redirect:/admin/posts";
    }
	
	
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
