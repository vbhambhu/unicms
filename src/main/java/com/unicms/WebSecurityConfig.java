package com.unicms;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		 
		 
		// System.out.println("==========================================================");
	   
	   auth.jdbcAuthentication().dataSource(dataSource)
	  .usersByUsernameQuery(
	   "select username,password, status from users where username=?")
	  .authoritiesByUsernameQuery(
	   "select u.username, r.name as role from users u LEFT JOIN user_roles r ON (u.id = r.id) where u.username=?");
	 } 
	  
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	 
	   http.authorizeRequests()
	   .antMatchers("/admin/login").permitAll()
	  .antMatchers("/admin/**").authenticated()
	  .anyRequest().permitAll()
	  .and()
	    .formLogin().loginPage("/admin/login").defaultSuccessUrl("/admin")
	    .usernameParameter("username").passwordParameter("password").loginProcessingUrl("/admin/login")
	  .and()
	    .logout().logoutSuccessUrl("/admin/login?logout") 
	   .and()
	   .exceptionHandling().accessDeniedPage("/403")
	  .and()
	    .csrf();
	 }
}