package com.sg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationLoginController {

	@GetMapping({ "/", "" })
	public String mainPage() {
		return "/home";
	}

	@GetMapping( "/login" )
	public String login() {
		return "/login";
	}

	@GetMapping( "/logout" )
	public String logout() {
		return "/logout";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration() {
		return "registration";
	}

}