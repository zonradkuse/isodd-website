package com.isodd.website.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class NpmRepositoryService {
	private static final String URL_PREFIX_NPM_PACKAGE = "https://www.npmjs.com/package/";
	
	public boolean repositoryExists(String repositoryName) {
		RestTemplate npmTemplate = new RestTemplate();
		String repositoryUrl = URL_PREFIX_NPM_PACKAGE + repositoryName;
		
		try {
			npmTemplate.getForObject(repositoryUrl, String.class);
		} catch (HttpClientErrorException clientError) {
			return false;
		}
		
		return true;
	}
}
