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
import org.springframework.web.bind.annotation.RequestParam;

import com.unicms.models.Post;
import com.unicms.models.User;
import com.unicms.services.PostService;

import groovyjarjarantlr.collections.List;

@Controller
public class AdminPost {
	
	@Autowired
	PostService postService;
	
	@RequestMapping(value="/admin/post", method=RequestMethod.GET)
	public String showPosts(Model model) {
		model.addAttribute("posts", postService.listPost() );
        return "admin/posts/list";
    }
	
	
	@RequestMapping(value="/admin/post/new", method=RequestMethod.GET)
	public String createPost(Post post, Model model) {
		
		model.addAttribute("categories", postService.listCategories());
		
		return "admin/posts/insert";
    }
	
	@RequestMapping(value="/admin/post/new", method=RequestMethod.POST)
	public String insertPost(@Valid Post post, BindingResult bindingResult, Model model) {
		
		model.addAttribute("categories", postService.listCategories());
		
		if (bindingResult.hasErrors()) {
			 return "admin/posts/insert";
        }
		
		if( postService.slugCount( post.getSlug() ) > 0 ){
			bindingResult.rejectValue("slug", "error.post", "Seo url already exists.");
		}
		
		if (bindingResult.hasErrors()) {
			return "admin/posts/insert";
        }
		
		//Create new post
		postService.createPost(post);

		
        return "redirect:/admin/post";
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
