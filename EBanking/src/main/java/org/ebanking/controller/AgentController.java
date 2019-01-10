//package org.ebanking.controller;
//
//import java.util.List;
//
//import org.ebanking.dao.ClientRepository;
//import org.ebanking.entity.Client;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.web.bind.annotation.RequestAttribute;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController()
//@RequestMapping(value = "/Agent")
////@Secured(value = {"ROLE_AGENT"})
//public class AgentController {
//
//	@Autowired
//	private ClientRepository clientRepository;
//
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
//
//	/**
//	 *
//	 */
//	@RequestMapping(value = "/addClient",method=RequestMethod.POST)
//	public Client addClient( Client client){
//		return clientRepository.save(client);
//	}
//	
//	@RequestMapping(value = "/editClient",method=RequestMethod.POST)
//	public Client editClient(Client client){
//		return clientRepository.save(client);
//	}
//
//	@RequestMapping(value = "/getAllClients")
//	public List<Client> getAllClients(){
//		return clientRepository.findAll();
//	}
//	
//	@RequestMapping(value="/activateCompte")
//	public Client activateCompte(int id_client)
//	{
//		System.out.println("--------------------------.....---------- "+id_client);
//		Client client =clientRepository.findById(id_client).get();
//		client.setActivated(true);
//		clientRepository.save(client);
//		return client;
//	}
//	
//	@RequestMapping(value="/desacivateCompte")
//	public Client desactivateCompte(int id_client)
//	{
//		Client client =clientRepository.findById(id_client).get();
//		client.setActivated(false);
//		clientRepository.save(client);
//		return client;
//	}
//	
//	@RequestMapping(value="/getClient")
//	public Client getClientbyId(int id_client)
//	{
//		Client client =clientRepository.findById(id_client).get();
//		return client;
//		
//	}
//	
//	@RequestMapping(value="/deleteClient",method=RequestMethod.DELETE)
//	public void deleteClientbyId(int id_client)
//	{
//		clientRepository.deleteById(id_client);
//		
//	}
//	
//
//	
//	
//
//
//
//}
