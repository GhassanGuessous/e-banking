package org.ebanking;

import org.ebanking.dao.AdminRepository;
import org.ebanking.dao.AgenceRepository;
import org.ebanking.dao.AgentRepository;
import org.ebanking.dao.CategorieServiceRepository;
import org.ebanking.dao.ClientRepository;
import org.ebanking.dao.RoleRepository;
import org.ebanking.dao.SousCategorieServiceRepository;
import org.ebanking.dao.VilleRepository;
import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;
import org.ebanking.entity.Agent;
import org.ebanking.entity.CategorieService;
import org.ebanking.entity.Client;
import org.ebanking.entity.Role;
import org.ebanking.entity.SousCategorieService;
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
	 
	 @Autowired
	 	private CategorieServiceRepository categorieServiceRepos;
	
	 @Autowired
	 	private SousCategorieServiceRepository sousCategorieServiceRepos;
	
	public static void main(String[] args) {
		SpringApplication.run(EBankingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
//		//Creation des roles	
//		Role role = new Role("ROLE_ADMIN");
//		Role role2 = new Role("ROLE_CLIENT");
//		Role role3 = new Role("ROLE_AGENT");
//		//Creation des villes
//		Ville casaVille =  new Ville("Casablanca");
//		Ville rabatVille =  new Ville("Rabat");
//		
//		//Creation des users
//		Admin admin = new Admin("admin1","lachg","bernoussi","0532","bfsgface","admin1",passwordEncod.encode("z"),"BB1",true,null,null,role);
//		Client client = new Client("client1","lachg2","bernoussi","05321","bfsgface2","client1",passwordEncod.encode("z"),"BB2",true,role,223,null);
//		client.setLimite(8000);
//		Admin admin2 = new Admin("admin2","lachg3","bernoussi","05323","bfsgface3","admin2",passwordEncod.encode("z"),"BB3",true,null,null,role);	
//		Agent agent = new Agent("agent1","lachg4","bernoussi","05324","bfsgface4","agent1",passwordEncod.encode("z"),"BB4",true,null,admin,role3);
//		Agent agent2 = new Agent("agent2","lachg5","bernoussi","05325","bfsgface5","agent2",passwordEncod.encode("z"),"BB5",true,null,admin,role3);
//		
//		// creation des agances 
//		Agence agence = new Agence("Banque Bernoussi","Bernoussi",casaVille,admin);
//		Agence agence2 = new Agence("Banque Rabat","RabatVille",rabatVille,admin);
//		
//		// creation des Categorie Service
//		
//		CategorieService categorieService = new CategorieService("Facture", null);
//		CategorieService categorieService2 = new CategorieService("Recharge", null);
//		
//		// creation des SousCategorie Service
//		
//		SousCategorieService sousCategorieService = new SousCategorieService("Facture Elecrtricite", categorieService);
//		SousCategorieService sousCategorieService2 = new SousCategorieService("Facture Credit", categorieService);
//		SousCategorieService sousCategorieService3 = new SousCategorieService("Facture wifi", categorieService);
//		SousCategorieService sousCategorieService4 = new SousCategorieService("Recharge Telephone", categorieService2);
//		
//		
//		//save CategorieService
//		categorieServiceRepos.save(categorieService);
//		categorieServiceRepos.save(categorieService2);
//		
//		//save sousCat
//		
//		sousCategorieServiceRepos.save(sousCategorieService);
//		sousCategorieServiceRepos.save(sousCategorieService2);
//		sousCategorieServiceRepos.save(sousCategorieService3);
//		sousCategorieServiceRepos.save(sousCategorieService4);
//		
//		
//		//save Roles
//		rolerep.save(role);
//		rolerep.save(role2);
//		rolerep.save(role3);
//		
//		//save ville
//		VilleRepository.save(casaVille);
//		VilleRepository.save(rabatVille);
//			
//		//Save admins 
//		adminRepository.save(admin);
//		adminRepository.save(admin2);
//		
//		//save agence 
//		agenceRepository.save(agence);
//		agenceRepository.save(agence2);
//		
//		//ajout agence a l'agent
//		
//		agent.setAgence(agence);
//		agent2.setAgence(agence2);
//		
//		//save agent
//		AgentRepository.save(agent);
//		AgentRepository.save(agent2);
//		
//		//ajout agence a le client
//		client.setAgent(agent);
//		clientRepository.save(client);
//		
	
				
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

