package com.isodd.website.controllers;

import com.isodd.website.services.HostToRepositoryConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.isodd.website.services.NpmRepositoryService;

import java.net.InetSocketAddress;

@Controller
public class IndexController {
	
	@Autowired
	private NpmRepositoryService npmRepositoryService;

	@Autowired
    private HostToRepositoryConverterService hostToRepositoryConverterService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
    public String messages(Model model, @RequestParam(required = false) String message, @RequestParam(required = false) String repository) {
        model.addAttribute("message", message);
        model.addAttribute("repository", repository);
        boolean repositoryExists = npmRepositoryService.repositoryExists(repository);
        model.addAttribute("repositoryExists", repositoryExists);
        return "index";
    }

    @RequestMapping(path = "/from-host", method = RequestMethod.GET)
    public String messagesFromHost(@RequestHeader HttpHeaders headers) {
        final InetSocketAddress host = headers.getHost();
        final String hostName = host.getHostName();
        final String repositoryName = hostToRepositoryConverterService.convertToRepository(hostName);

        return String.format("forward:/?repository=%s", repositoryName);
    }
}
