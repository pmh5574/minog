package com.minog.minog;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//@EnableJpaAuditing // JPA Auditing 활성화
//@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class MinogApplication {
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,";

	public static void main(String[] args) {
		new SpringApplicationBuilder(MinogApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
