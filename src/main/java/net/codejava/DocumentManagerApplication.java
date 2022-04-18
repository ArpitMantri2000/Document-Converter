package net.codejava;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class DocumentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentManagerApplication.class, args);
	}
	

	
	@Configuration
	public class CorsFilter implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedMethods("GET")
	                .allowedMethods("PUT")
	                .allowedMethods("POST")
	                .allowedMethods("DELETE")
	                .allowedHeaders("Access-Control-Allow-Origin", "*").allowedHeaders("Access-Control-Allow-Credentials", "true");
	}
	}

}