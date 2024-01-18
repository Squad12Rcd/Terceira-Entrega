package com.connectjob.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class HomeController {

	@GetMapping("/")
	public String Home(Model model) { 
		return "index";
	}
}