package com.betterjavacode.elasticsearchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.betterjavacode.elasticsearchdemo"})
public class ElasticsearchdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchdemoApplication.class, args);
	}

}
