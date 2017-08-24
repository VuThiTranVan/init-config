package com.framgia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AcessDeniedController {
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {		
		return "acessdeneid";
	}
}
