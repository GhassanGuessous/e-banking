package org.ebanking;

import org.ebanking.dao.AdminRepository;
import org.ebanking.dao.AgenceRepository;
import org.ebanking.dao.AgentRepository;
import org.ebanking.dao.ClientRepository;
import org.ebanking.dao.RoleRepository;
import org.ebanking.dao.VilleRepository;
import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;
import org.ebanking.entity.Agent;
import org.ebanking.entity.Client;
import org.ebanking.entity.Role;
import org.ebanking.entity.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EBankingApplication  implements CommandLineRunner{

	 @Autowired
	    private AdminRepository adminRepository;	
	 
	 @Autowired
	    private ClientRepository clientRepository;	
	 
	 @Autowired
	 	private AgentRepository AgentRepository;
	 
	 @Autowired
	 	private AgenceRepository agenceRepository;

	 @Autowired
	 	private VilleRepository VilleRepository;
	 
	 @Autowired
	 	private RoleRepository rolerep;
	 
	 @Autowired
	 	private PasswordEncoder passwordEncod;
	
	public static void main(String[] args) {
		SpringApplication.run(EBankingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("run");
		//Creation des roles	
		Role role = new Role("ROLE_ADMIN");
		Role role2 = new Role("ROLE_CLIENT");
		Role role3 = new Role("ROLE_AGENT");
		//Creation des villes
		Ville casaVille =  new Ville("Casablanca");
		Ville rabatVille =  new Ville("Rabat");
		
		//Creation des users
		Admin admin = new Admin("admin1","lachg","bernoussi","0532","bfsgface","admin1",passwordEncod.encode("z"),"BB",true,null,null,role);
		Client client = new Client("client1","lachg2","bernoussi","05321","bfsgface2","client1",passwordEncod.encode("z"),"BB",true,233,null,role2);
		Admin admin2 = new Admin("admin2","lachg3","bernoussi","05323","bfsgface3","admin2",passwordEncod.encode("z"),"BB",true,null,null,role);	
		Agent agent = new Agent("agent1","lachg3","bernoussi","05323","bfsgface3","agent1",passwordEncod.encode("z"),"BB",true,null,admin,role3);
		Agent agent2 = new Agent("agent2","lachg3","bernoussi","05323","bfsgface3","agent2",passwordEncod.encode("z"),"BB",true,null,admin,role3);
		
		// creation des agances 
		Agence agence = new Agence("Banque Bernoussi","Bernoussi",casaVille,admin);
		Agence agence2 = new Agence("Banque Rabat","RabatVille",rabatVille,admin);
		
		//save Roles
		rolerep.save(role);
		rolerep.save(role2);
		rolerep.save(role3);
		
		//save ville
		VilleRepository.save(casaVille);
		VilleRepository.save(rabatVille);
			
		//Save admins 
		adminRepository.save(admin);
		adminRepository.save(admin2);
		
		//save agence 
		agenceRepository.save(agence);
		agenceRepository.save(agence2);
		
		//ajout agence a l'agent
		
		agent.setAgence(agence);
		agent2.setAgence(agence2);
		
		//save agent
		AgentRepository.save(agent);
		AgentRepository.save(agent2);
		
		//ajout agence a le client
		client.setAgent(agent);
		clientRepository.save(client);
		
	
		
		
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

