package com.shodom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.shodom.service.MongoDBUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ShodomSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MongoDBUserService mongoDBUserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// authorize requests
		http.authorizeRequests()
				.antMatchers("/", "/index", "/login", "/logout", "/403", "/css/**", "/js/**", "/fonts/**", "/ico/**",
						"/locales/**", "/img/**", "/detail/**", "/register", "/getUser/**", "/addUser",
						"/postComment/**", "/listEntry/**")
				.permitAll().anyRequest().authenticated();

		// login configuration
		http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/").defaultSuccessUrl("/");

		// remember me configuration
		http.rememberMe().key("remember-me").rememberMeParameter("remember-me")
				.rememberMeCookieName("remember-me").tokenValiditySeconds(31536000);

		// logout configuration
		http.logout().logoutSuccessUrl("/");

		// exception handling
		http.exceptionHandling().accessDeniedPage("/403");
		
		// security
		http.csrf().disable();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(mongoDBUserService);
	}
}
