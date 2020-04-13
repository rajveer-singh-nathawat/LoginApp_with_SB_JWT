package com.example.demo.securityconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.DispatcherServlet;

import com.example.demo.filter.JwtRequestFilter;
import com.example.demo.service.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityCongiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailService userDetailService;
	
	@Autowired
	JwtRequestFilter requestFilter;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/admin/data").hasRole("ADMIN")
			.antMatchers("/user/data").hasAnyRole("ADMIN","USER")
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
			.requestMatchers(CorsUtils::isCorsRequest).permitAll()
			.antMatchers("/authenticate","/register", "/signin").permitAll().and()
			.formLogin().disable()
			
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		return NoOpPasswordEncoder.getInstance() ;
	}
	

}
