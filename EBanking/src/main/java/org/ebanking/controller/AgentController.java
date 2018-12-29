package org.ebanking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {

	/**
	 * return the connected user and his authorities
	 */
	@RequestMapping(value = "/logedUser")
	public Map<String, Object> getLogedUser(HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		
		String username = securityContext.getAuthentication().getName();
		List<String> roles = new ArrayList<>();
		
		for (GrantedAuthority authority : securityContext.getAuthentication().getAuthorities()) {
			roles.add(authority.getAuthority());
		}
		
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("role", roles);
		
		return params;
	}
}
