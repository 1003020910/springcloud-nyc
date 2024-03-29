package com.nyc.stydy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
	@Autowired
	RestTemplate restTemplate;

	public String getHelloContent(String name) {
		return restTemplate.getForObject("http://eureka-client/hello?name=111", String.class);
	}
}
