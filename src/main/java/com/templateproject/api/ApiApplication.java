package com.templateproject.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.templateproject.api.service.AuthService;
import com.templateproject.api.service.ProvinceService;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner{

private AuthService authService;
    private  ProvinceService provinceservice;


	public ApiApplication(AuthService authService, ProvinceService provinceservice){
this.authService =authService;
this.provinceservice = provinceservice;


	}


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

/* var idPlayer = authService.register("a","a","a","a");
provinceservice.add("a", idPlayer);

var idPlayer2 = authService.register("b","b","b","b");
provinceservice.add("b", idPlayer2); */

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
