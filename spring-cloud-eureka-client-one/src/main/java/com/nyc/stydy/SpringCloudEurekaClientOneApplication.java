package com.nyc.stydy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpringCloudEurekaClientOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientOneApplication.class, args);
	}
	@Value("${server.port}")
	String port;

	@GetMapping("/hello")
	public String home(@RequestParam String name) {
		return "hello world from port " + port;
	}
}
