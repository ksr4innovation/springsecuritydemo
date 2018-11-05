package com.snkit.inmemoryseucirty;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication().withUser("sairam").password("{bcrypt}$2a$10$wx1tZlevYkCet8kEH2HDcubDDOxe3i/ItVH9MSq.NybeHTzrnLpzC").authorities("REPORTS")
	    .and().withUser("ramsai").password("{bcrypt}$2a$10$RHZfMkcBbtpbeAFkHNwmBOuNYP.gK3QwsWBks7BNlkz/dO98uCXOq").authorities("ADMIN");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
	

		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()			
			.httpBasic();
	}
	
	

}
