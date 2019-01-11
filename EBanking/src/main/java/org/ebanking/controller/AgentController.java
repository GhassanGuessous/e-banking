package org.ebanking.controller;

import java.util.List;

import org.ebanking.dao.AgentRepository;
import org.ebanking.dao.ClientRepository;
import org.ebanking.entity.Agent;
import org.ebanking.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/Agent")
//@Secured(value = {"ROLE_AGENT"})
public class AgentController {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AgentRepository agentRepository;

//	/**
//	 * return the connected user and his authorities
//	 */
////	@RequestMapping(value = "/logedUser")
////	public Map<String, Object> getLogedUser(HttpServletRequest httpServletRequest){
////		HttpSession httpSession = httpServletRequest.getSession();
////		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
////		
////		String username = securityContext.getAuthentication().getName();
////		List<String> roles = new ArrayList<>();
////		
////		for (GrantedAuthority authority : securityContext.getAuthentication().getAuthorities()) {
////			roles.add(authority.getAuthority());
////		}
////		
////		Map<String, Object> params = new HashMap<>();
////		params.put("username", username);
////		params.put("role", roles);
////		
////		return params;
////	}

	@RequestMapping(value = "/addClient",method=RequestMethod.POST)
	public Client addClient(@RequestBody Client client){
		Agent ag=agentRepository.findByUsername(client.getAgent().getUsername());
		client.setAgent(ag);
		return clientRepository.save(client);
	}
	
	@RequestMapping(value = "/editClient",method=RequestMethod.POST)
	public Client editClient(@RequestBody Client client){
		Client cl= clientRepository.findById(client.getId());
		cl.setActivated(client.isActivated());
		cl.setAdresse(client.getAdresse());
		cl.setCin(client.getCin());
		cl.setCodePostal(client.getCodePostal());
		cl.setEmail(client.getEmail());
		cl.setNom(client.getNom());
		cl.setPrenom(client.getPrenom());
		cl.setTelephone(client.getTelephone());
		return clientRepository.save(cl);
	}
	
	@RequestMapping(value = "/tst",method=RequestMethod.POST)
	public List<Client> tst(){
		return clientRepository.findAll();
	}

	@RequestMapping(value = "/getAllClients")
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}
	
	@RequestMapping(value="/activateCompte")
	public Client activateCompte(int id_client)
	{
		System.out.println("--------------------------.....---------- "+id_client);
		Client client =clientRepository.findById(id_client);
		client.setActivated(true);
		clientRepository.save(client);
		return client;
	}
	
	@RequestMapping(value="/desactivateCompte")
	public Client desactivateCompte(int id_client)
	{
		Client client =clientRepository.findById(id_client);
		client.setActivated(false);
		clientRepository.save(client);
		return client;
	}
	
	@RequestMapping(value="/getClient")
	public Client getClientbyId(int id_client)
	{
		Client client =clientRepository.findById(id_client);
		return client;
		//return clientRepository.findById(id_client);

		
	}
	
	@RequestMapping(value="/deleteClient",method=RequestMethod.DELETE)
	public void deleteClientbyId(int id_client)
	{
		clientRepository.deleteById(id_client);
		
	}
	

	
	



}
