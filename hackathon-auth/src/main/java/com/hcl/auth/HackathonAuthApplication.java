package com.hcl.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
public class HackathonAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonAuthApplication.class, args);
	}
}
