package com.minog.minog;

import com.minog.minog.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class MinogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinogApplication.class, args);
	}

}
