package com.shodom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
		http.formLogin().defaultSuccessUrl("/")
        .and().logout().and().authorizeRequests()
        .antMatchers("/", "/index", "/login", "/logout", "/403").permitAll().anyRequest()
        .authenticated()
        .and()
		  .logout().logoutSuccessUrl("/")
        .and()
		  .exceptionHandling().accessDeniedPage("/403")
        .and().csrf().disable();

	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**");
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(mongoDBUserService);
	}
}
