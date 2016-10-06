package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer /* To enable this as config server where micro service will register */
public class SpringcloudconfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudconfigserverApplication.class, args);
	}
}