package com.example.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomLoginSeccessHandler loginSuccessHundler;
	
	@Autowired
	UserDetailsService userDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasAnyRole("USER","ADMIN","EXPERT")
		.antMatchers("/expert/**").hasAnyRole("USER","EXPERT","ADMIN")
		.antMatchers("/expert_insurance/**").hasAnyRole("USER","ADMIN","IEXPERT")
		.antMatchers("/").permitAll()
		.and().formLogin().loginPage("/login").successHandler(loginSuccessHundler);
		http.csrf().disable();// for more information about csrf Token https://www.yawintutor.com/how-to-enable-and-disable-csrf
	}

	@Bean 
	public PasswordEncoder getPasswordEncoder(){return NoOpPasswordEncoder.getInstance();}

}
