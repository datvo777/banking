package com.tpi.banking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableScheduling
public class AppConfig {
	@Bean
	public ObjectMapper objectMapper () {
		return new ObjectMapper();
	}
}
