package org.ebanking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ebanking.dao.ClientRepository;
import org.ebanking.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/Agent")
//@Secured(value = {"ROLE_AGENT"})
public class AgentController {

	@Autowired
	private ClientRepository clientRepository;

	/**
	 * return the connected user and his authorities
	 */
//	@RequestMapping(value = "/logedUser")
//	public Map<String, Object> getLogedUser(HttpServletRequest httpServletRequest){
//		HttpSession httpSession = httpServletRequest.getSession();
//		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
//		
//		String username = securityContext.getAuthentication().getName();
//		List<String> roles = new ArrayList<>();
//		
//		for (GrantedAuthority authority : securityContext.getAuthentication().getAuthorities()) {
//			roles.add(authority.getAuthority());
//		}
//		
//		Map<String, Object> params = new HashMap<>();
//		params.put("username", username);
//		params.put("role", roles);
//		
//		return params;
//	}

	/**
	 *
	 */
	@RequestMapping(value = "/addClient")
	public Client addClient(Client client){
		return clientRepository.save(client);
	}

	@RequestMapping(value = "/getAllClients")
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}



}
