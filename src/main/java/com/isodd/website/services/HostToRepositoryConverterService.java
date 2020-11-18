package com.isodd.website.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class HostToRepositoryConverterService {
	private static final String SUBDOMAIN_SEPARATOR = "\\.";
	private static final String REPOSITORY_NAME_WORD_SEPARATOR = "-";

	public String convertToRepository(String hostName) {
		final String repositoryName = hostName.replaceAll(SUBDOMAIN_SEPARATOR, REPOSITORY_NAME_WORD_SEPARATOR);
		return repositoryName;
	}
}
