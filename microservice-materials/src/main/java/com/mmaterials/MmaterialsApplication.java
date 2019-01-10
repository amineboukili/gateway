package com.mmaterials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class MmaterialsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmaterialsApplication.class, args);
	}
}
