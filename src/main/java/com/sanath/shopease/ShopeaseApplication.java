package com.sanath.shopease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.sanath.shopease.model")
public class ShopeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopeaseApplication.class, args);
	}

}
