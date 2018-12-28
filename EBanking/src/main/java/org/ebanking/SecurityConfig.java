package org.ebanking;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
		
		// from database
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.usersByUsernameQuery("select username as principal, password as credentials, true from agent where username = ?")
		.authoritiesByUsernameQuery("select a.username as principal, r.role from agent a join role r on a.role_role = r.role where a.username = ?")
		.rolePrefix("ROLE_");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// bloquer l'acces a toute l'app et si l user n'est pas auth. le rediriger vers /login
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
					.anyRequest()
						.authenticated()
							.and()
		.formLogin()
			.loginPage("/login")
				.permitAll()
		.defaultSuccessUrl("/index.html");
	}
}
