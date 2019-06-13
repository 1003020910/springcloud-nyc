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
public class SpringCloudServiceHiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceHiApplication.class, args);
	}
	private static final Logger LOG = Logger.getLogger(SpringCloudServiceHiApplication.class.getName());


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hello")
    public String hello(){
        LOG.log(Level.INFO, "hey开始调用跟踪了");
        return "你好呀，这里是service-hey";
    }
    @RequestMapping("/hey")
    public String info(){
        LOG.log(Level.INFO, "hey开始调用跟踪了");
        return restTemplate.getForObject("http://localhost:9001/info",String.class);
    }

    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
