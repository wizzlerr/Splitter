package com.ootb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;

@SpringBootApplication(exclude = VelocityAutoConfiguration.class)
public class SplitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitterApplication.class, args);
	}
}
