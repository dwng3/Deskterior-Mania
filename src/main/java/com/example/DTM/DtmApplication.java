package com.example.DTM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DtmApplication.class, args);
	}

}
