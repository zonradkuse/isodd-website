package com.isodd.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.isodd.website.services.NpmRepositoryService;

@Controller
public class IndexController {
	
	@Autowired
	private NpmRepositoryService npmRepositoryService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
    public String messages(Model model, @RequestParam(required = false) String message, @RequestParam(required = false) String repository) {
        model.addAttribute("message", message);
        model.addAttribute("repository", repository);
        boolean repositoryExists = npmRepositoryService.repositoryExists(repository);
        model.addAttribute("repositoryExists", repositoryExists);
        return "index";
    }
}
