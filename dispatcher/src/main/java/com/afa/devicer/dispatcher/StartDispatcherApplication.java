package com.afa.devicer.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class StartDispatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartDispatcherApplication.class, args);
	}

}
