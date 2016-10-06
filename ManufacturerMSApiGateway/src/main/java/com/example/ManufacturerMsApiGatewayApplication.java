package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient // Config server enable Discovery this service
@EnableZuulProxy

public class ManufacturerMsApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManufacturerMsApiGatewayApplication.class, args);
	}
}
