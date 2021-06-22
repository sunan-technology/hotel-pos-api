package com.sunan;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebConfig  {


	@Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("Access-Control-Allow-Origin", "Content-Type", "X-Requested-With");
            }
        };
    }
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
//	 @Bean
//	    public JavaMailSender javaMailSender() { 
//	          return new JavaMailSender();
//	    }
	
//	 @Bean
//     public PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//     }
}