package com.musers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class MusersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusersApplication.class, args);
	}
}
