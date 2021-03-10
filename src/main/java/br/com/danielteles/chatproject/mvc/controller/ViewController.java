package br.com.danielteles.chatproject.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	
	@RequestMapping("/")
	public ModelAndView home() {
		return new ModelAndView("home.html");
	}
}
