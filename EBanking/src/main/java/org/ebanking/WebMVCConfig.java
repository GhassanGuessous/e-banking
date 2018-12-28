/**
 * 
 */
package org.ebanking;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Ghassan
 * 
 * equivalent de creer un controller spring
 * contenant une methode qui fait la redirection
 * 
 */
@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("loginForm");
		registry.addViewController("/logout").setViewName("loginForm");
		
	}
}
