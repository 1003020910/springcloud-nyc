package com.nyc.stydy;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@SpringBootApplication
@RestController
public class SpringCloudServiceHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceHelloApplication.class, args);
	}
	private static final Logger LOG = Logger.getLogger(SpringCloudServiceHelloApplication.class.getName());


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hello")
    public String hello(){
        LOG.log(Level.INFO, "开始调用跟踪service-hello");
        return restTemplate.getForObject("http://localhost:9002/hey", String.class);
    }
    @RequestMapping("/info")
    public String info(){
        LOG.log(Level.INFO, "开始调用跟踪service-hello");
        return "你好呀，这里是service-hello";

    }
    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
