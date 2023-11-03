package com.project.TasteTreasure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.TasteTreasure")
public class TasteTreasureApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasteTreasureApplication.class, args);
	}
}
