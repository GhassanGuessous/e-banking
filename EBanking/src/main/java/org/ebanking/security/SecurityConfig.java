package org.ebanking.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Autowired
//	public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
//		
//		// from database
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		.passwordEncoder(NoOpPasswordEncoder.getInstance())
//		.usersByUsernameQuery("select username as principal, password as credentials, true from agent where username = ?")
//		.authoritiesByUsernameQuery("select a.username as principal, r.role from agent a join role r on a.role_role = r.role where a.username = ?")
//		.rolePrefix("ROLE_");
//		
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		// bloquer l'acces a toute l'app et si l user n'est pas auth. le rediriger vers /login
//		http
//			.csrf().disable()
//			.authorizeRequests()
//				.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
//					.anyRequest()
//						.authenticated()
//							.and()
//		.formLogin()
//			.loginPage("/login")
//				.permitAll()
//		.defaultSuccessUrl("/index.html");
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
			.antMatchers("/login/**").permitAll()
			.antMatchers(HttpMethod.POST, "/banquiers/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.POST, "/client/**").hasAuthority("AGENT")
			.anyRequest().authenticated()
		.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
    }
}
