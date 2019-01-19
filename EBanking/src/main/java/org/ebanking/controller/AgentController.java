package org.ebanking.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.ebanking.dao.AgentRepository;
import org.ebanking.dao.ClientRepository;
import org.ebanking.dao.CompteRepository;
import org.ebanking.dao.ReclamationRepository;
import org.ebanking.dao.RoleRepository;
import org.ebanking.entity.Agent;
import org.ebanking.entity.Client;
import org.ebanking.entity.Compte;
import org.ebanking.entity.Reclamation;
import org.ebanking.web.inputs.ClientInputByAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/Agent")
//@Secured(value = {"ROLE_AGENT"})
public class AgentController {
	public static final String datePattern = "dd/MM/yyyy HH:mm:ss";

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private ReclamationRepository reclamationRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	@RequestMapping(value = "/addClient",method=RequestMethod.POST)
	public Client addClient(@Valid @RequestBody ClientInputByAgent clientInput) throws ParseException{
		if(clientInput.getPassword().equals(clientInput.getConfirmedPassword())) {
		Client client=substitute(clientInput);
		client= clientRepository.save(client);
		Compte compte = createCompte(client);
		compteRepository.save(compte);
		return client;
		}
		else return null;
	}
	
	private Client substitute(ClientInputByAgent clientInput) {
		Client client = new Client();
		Agent agent=agentRepository.findByUsername(clientInput.getAgent().getUsername());
		client.setAgent(agent);
		client.setCin(clientInput.getCin());
		client.setPrenom(clientInput.getPrenom());
		client.setNom(clientInput.getNom());
		client.setAdresse(clientInput.getAdresse());
		client.setEmail(clientInput.getEmail());
		client.setCodePostal(clientInput.getCodePostal());
		client.setTelephone(clientInput.getTelephone());
		client.setUsername(clientInput.getUsername());
		client.setPassword(encoder.encode(clientInput.getPassword()));
		client.setRole(roleRepository.findRoleByRole("ROLE_CLIENT"));
		client.setActivated(true);
		return client;
	}
	
	private Compte createCompte(Client client) throws ParseException {
		DateFormat df = new SimpleDateFormat(datePattern);
		Compte compte = new Compte();

		// rib : 16 chiffres
		compte.setRib(generateRib());
		compte.setSold(new Double(0.0));
		compte.setDateCreation(df.parse(df.format(Calendar.getInstance().getTime())));
		compte.setClient(client);
		compte.setAgent(client.getAgent());
		return compte;
	}
	private Long generateRib() {
		Long rib;
		Compte isExist;

		do {
			rib = (long) (Math.random() * 10000000000000000L);
			isExist = compteRepository.findByRib(rib);
		} while (isExist != null);

		return rib;
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
	public List<Client> getAllClients(String username){
		Agent agent=agentRepository.findByUsername(username);
		return clientRepository.findByAgent(agent);
	}
	
	@RequestMapping(value = "/getAllNotActivatedClients")
	public List<Client> getAllNotActivatedClients(){
		return clientRepository.findByActivated(false);
	}
	
	@RequestMapping(value="/activateCompte",method=RequestMethod.POST)
	public Client activateCompte(@RequestBody Client client)
	{
		Client cl =clientRepository.findById(client.getId());
		cl.setActivated(true);
		cl.setAgent(agentRepository.findByUsername(client.getAgent().getUsername()));
		cl.setRole(roleRepository.findRoleByRole("ROLE_CLIENT"));
		clientRepository.save(cl);
		return cl;
	}
	@RequestMapping(value="/switchActivation",method=RequestMethod.POST)
	public Client switchActivation(@RequestBody Client client)
	{
		Client cl =clientRepository.findById(client.getId());
		if(client.isActivated())
			cl.setActivated(false);
		else cl.setActivated(true);
		clientRepository.save(cl);
		return cl;
	}
	@RequestMapping(value="/desactivateCompte",method=RequestMethod.POST)
	public Client desactivateCompte(@RequestBody Client cl)
	{
		Client client =clientRepository.findById(cl.getId());
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
	public void deleteClientbyId(@RequestBody Client client)
	{
		Client cl=clientRepository.findById(client.getId());
		clientRepository.delete(cl);
		
	}
	
	@RequestMapping(value="/getReclamations",method=RequestMethod.GET)
	public List<Reclamation> getReclamations()
	{
		return reclamationRepository.findByVerifie(false);
		
	}
	@RequestMapping(value="/verifyRec",method=RequestMethod.POST)
	public Reclamation verify(@RequestBody Reclamation rec)
	{
		Reclamation recl=reclamationRepository.findById(rec.getId());
		recl.setAgent(agentRepository.findByUsername(rec.getAgent().getUsername()));
		recl.setVerifie(true);
		recl.setEtat("Verifié");
		return reclamationRepository.save(recl);
		
	}
	
	



}
