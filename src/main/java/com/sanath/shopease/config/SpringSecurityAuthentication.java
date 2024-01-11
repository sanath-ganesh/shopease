package com.sanath.shopease.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.sanath.shopease.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityAuthentication {

	@Autowired
	UserService userService;

	InMemoryUserDetailsManager userDetailsManager;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests((req) -> {
			try {
				req.requestMatchers("/", "/users/register", "/users/register.htm", "/css/**", "/images/**").permitAll()
						.anyRequest().authenticated().and().csrf().disable();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).formLogin();
		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		userDetailsManager = new InMemoryUserDetailsManager();

		userService.getAllUsers().forEach(user -> {
			UserDetails userDetails = users.username(user.getUserName()).password(user.getPassword()).roles("USER")
					.build();
			userDetailsManager.createUser(userDetails);
		});

		return userDetailsManager;
	}

	public void reloadUserDetails(com.sanath.shopease.model.User user) {
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails userDetails = users.username(user.getUserName()).password(user.getPassword()).roles("USER").build();
		userDetailsManager.createUser(userDetails);
	}

}
