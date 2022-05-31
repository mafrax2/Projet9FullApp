package com.openclassroom.mediscreenclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassroom")
public class MediscreenClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenClientApplication.class, args);
	}

}
