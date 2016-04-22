package com.unicms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	 public String showHome() {
	        return "home";
	 }
	
	
	@RequestMapping(value="/category/{slug}", method=RequestMethod.GET)
	public String showArticle(Model model, @PathVariable String slug) {
	        return "home";
	}
}