package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(DemoApplication.class, args);
	}

}
