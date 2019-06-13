package com.nyc.stydy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "error")
	public String getHelloContent(String name) {
		return restTemplate.getForObject("http://config-client/hello?name=1111", String.class);
	}

    public String error(String name) {
        return "哎呀呀，"+name+",出错了呀!";
    }
}
