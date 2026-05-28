package com.work.ProductRest.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfiguration {
	
	@Value("${cloudinary.api_key}")
	private String api_key;
	
	@Value("${cloudinary.secret_key}")
	private String secret_key;
	
	@Value("${cloudinary.cloud_name}")
	private String cloud_name;

	@Bean
	public Cloudinary getCloudinary() {
		HashMap<String, String> config = new HashMap<>();
		config.put("cloud_name", cloud_name);
		config.put("api_key", api_key);
		config.put("api_secret", secret_key);
	
		
		return new Cloudinary(config);
	}

}
