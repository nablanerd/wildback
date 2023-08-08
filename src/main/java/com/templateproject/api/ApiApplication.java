package com.templateproject.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.templateproject.api.service.AuthService;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner{

private AuthService authService;

	public ApiApplication(AuthService authService){
this.authService =authService;

	}


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

authService.register("a","a","a","a");
authService.register("b","b","b","b");

	}
	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins(new String[] {
						"http://localhost:4200",
						"http://localhost:8080"
				})
				.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
		        .allowedHeaders("Content-Type", "Date", "Total-Count", "loginInfo", "Authorization", "x-token")
		        .exposedHeaders("Authorization", "x-token")
		        .allowCredentials(true);
			}
		};
	}*/
	
}
