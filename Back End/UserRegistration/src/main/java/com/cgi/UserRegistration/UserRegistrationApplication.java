package com.cgi.UserRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cgi.UserRegistration.jwtFilter.JWTfilter;

@SpringBootApplication
public class UserRegistrationApplication {
	@Bean
	public FilterRegistrationBean jwtFilter() {
	final FilterRegistrationBean  registrationBean=new FilterRegistrationBean();
	registrationBean.setFilter(new JWTfilter());
	registrationBean.addUrlPatterns("/user/secure123/*");
	return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationApplication.class, args);
	}
}
