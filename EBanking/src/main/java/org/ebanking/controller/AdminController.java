package org.ebanking.controller;

import org.ebanking.dto.AddAgentDTO;
import org.ebanking.dto.AdmintDTO;
import org.ebanking.dto.AgenceDTO;
import org.ebanking.dto.AgentDTO;
import org.ebanking.dto.CategorieServiceDTO;
import org.ebanking.dto.ClientDTO;
import org.ebanking.dto.SousCategorieDTO;
import org.ebanking.dao.*;

import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;
import org.ebanking.entity.Agent;
import org.ebanking.entity.CategorieService;
import org.ebanking.entity.Client;
import org.springframework.beans.BeanUtils;

import org.ebanking.entity.Role;
import org.ebanking.entity.SousCategorieService;
import org.ebanking.entity.Ville;
import org.ebanking.security.SecurityConstants;
import org.ebanking.web.inputs.AgenceInput;
import org.ebanking.web.inputs.AgentInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.beans.Beans;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/Admin")
//@Secured(value = {"ROLE_ADMIN"})
public class AdminController {
	
    @Autowired
 	private ClientRepository clientRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AgenceRepository ageenceRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
 	private CategorieServiceRepository categorieServiceRepos;
    @Autowired
 	private SousCategorieServiceRepository sousCategorieServiceRepos;

    
   //quelque CRUD dont j'ai eu besoin f FRONT  -- Zakaria Lachguar
    
    //Test Token Validation
    @GetMapping(value="/tokenTest")
    public String testToken()
    {
    	return "succes";
    }
    
    @RequestMapping(value = "/getVilles", method = RequestMethod.GET)
    public List<Ville> getAllVilles(){
    		return villeRepository.findAll();
    }
    
    @RequestMapping(value = "/getClients", method = RequestMethod.GET)
	public List<ClientDTO> getAllClients(){
    	
	   	List<ClientDTO> dtoClients = new ArrayList<>();
        List<Client> clients=  clientRepository.findAll();	       
	   	for(Client client : clients)
	       {
	   		ClientDTO clientDTO = new ClientDTO();
	       	BeanUtils.copyProperties(client,clientDTO);
	       	clientDTO.setAgent(client.getAgent().getNom());
	       	System.out.println(client.getLimite());
	       	System.out.println(clientDTO.getLimite());

	       	dtoClients.add(clientDTO);
	       	
	       }
    	return dtoClients;
	   }
    
    @RequestMapping(value = "/setLimite/{id}/{limite}", method = RequestMethod.POST)
    public void setClientimite(@PathVariable int id ,@PathVariable double limite ){
    	
    	Client client = clientRepository.findById(id);
    	if(client!=null) {
    	client.setLimite(limite);
    	clientRepository.save(client);
    	}else
    	{
            throw new RuntimeException("Client inexsitant !");       
    	}
    }
   
   
    @RequestMapping(value = "/addAdmin")
    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    @RequestMapping(value = "/getAllAdmins")
    public List<AdmintDTO> getAll(){
        
    	List<AdmintDTO> dtoAdmins = new ArrayList<>();
        List<Admin> admins=  adminRepository.findAll();
       
    	for(Admin admin : admins)
        {
    		AdmintDTO adminDTO = new AdmintDTO();
        	BeanUtils.copyProperties(admin,adminDTO);
        	dtoAdmins.add(adminDTO);
        }
    	return dtoAdmins;
    }
    
    //CRUD AGENT
    /**
     *
     * @param agentDTO
     * @return
     */
    @RequestMapping(value = "/addNewAgent", method = RequestMethod.POST)
    public Agent addAgent(@RequestBody @Valid AddAgentDTO agentDTO){
        try {
        	Agent agent = new Agent();
        	BeanUtils.copyProperties(agentDTO,agent);
        	agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        	agent.setAgence(ageenceRepository.findByNom(agentDTO.getAgence()));
        	agent.setAdmin(adminRepository.findByUsername(agentDTO.getAdmin()));
        	agent.setRole(roleRepository.findRoleByRole("ROLE_AGENT"));
            
        	
        	return agentRepository.save(agent);
        	//return agentRepository.save(agent);
        	
        }catch (Exception e) {
            throw new RuntimeException("Valeur en double détectée pour les champs EMAIL ou USERNAME !");       
        }

    }

    /**
     *
     * @return
     */
    
    @RequestMapping(value = "/getAllAgents")
    public List<AgentDTO> getAllAgents(){
        
        List<AgentDTO> agentdtos = new ArrayList<>();
        
        List<Agent> agents = agentRepository.findAll();
        
        for(Agent agent : agents ) {
        	//System.out.println(agent.getUsername());
        	AgentDTO agentDTO = new AgentDTO();
        	BeanUtils.copyProperties(agent,agentDTO);
        	agentDTO.setAgence(agent.getAgence().getNom());
        	agentDTO.setAdmin(agent.getAdmin().getUsername());
        	agentdtos.add(agentDTO);
        }
        
        return agentdtos;
}
    
    @RequestMapping(value = "/getAgent")
    public AgentDTO getAgent(String username){

        AgentDTO agentDTO = new AgentDTO();
        
    	Agent agent = agentRepository.findByUsername(username);
    	
    	BeanUtils.copyProperties(agent,agentDTO);
    	agentDTO.setAgence(agent.getAgence().getNom());
    	agentDTO.setAdmin(agent.getAdmin().getUsername());
    	
    	return  agentDTO;
    }
    
    /**
    *
    * @param AgentDTO
    * @return
    */
    @RequestMapping(value = "/updateAgent",method=RequestMethod.POST)
    public Agent UpdateAgent(@RequestBody @Valid AgentDTO agentDTO , BindingResult binres){
       
    	if(binres.hasErrors())
		{
    		throw new RuntimeException("Valeur en double détectée pour les champs EMAIL ou USERNAME !");
		}
    	else
    	{
    	Optional<Agent> opagent = agentRepository.findById(agentDTO.getId());
    	Agent agent = opagent.get();   	
    	BeanUtils.copyProperties(agentDTO,agent);
    	System.out.println(agent.getNom());
    	Admin admin =adminRepository.findByUsername(agentDTO.getAdmin());
    	Agence agence = ageenceRepository.findByNom(agentDTO.getAgence());
    	agent.setAgence(agence);
    	agent.setAdmin(admin);
    	
     	return	agentRepository.save(agent);

    	}
       
         
    }
   
    
    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteAgent/{id}", method = RequestMethod.POST)
    public void deleteAgent(@PathVariable int id){

        Agent agent = agentRepository.findAgentById(id);

        if (agent != null) {
           try {
        	   agentRepository.delete(agent);
           }catch (Exception e) {
               throw new RuntimeException("Cet Agent a deja des Clients !");
		}
            
        }
        else
            throw new RuntimeException("No Agent found with id(" + id + ") !");

    }

 
    
    


    // CRUD Agences ::

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getAgences", method = RequestMethod.GET)
    public List<AgenceDTO> getAllAgences(){
    	List<AgenceDTO> agenceDtos = new ArrayList<>();
    	
    	List<Agence>agences=agenceRepository.findAll();
    	
    	for(Agence agence : agences)
    	{
    		AgenceDTO agenceDto = new AgenceDTO();
    		BeanUtils.copyProperties(agence,agenceDto);
    		agenceDto.setAdmin(agence.getAdmin().getUsername());
    		agenceDto.setVille(agence.getVille().getNom());
        	agenceDtos.add(agenceDto);
    	}
    	return agenceDtos;
    }
    
    
    

    /**
     *
     * @param agenceDTO
     * @return
     */
    @RequestMapping(value = "/addNewAgence", method = RequestMethod.POST)
    public AgenceDTO addAgence(@RequestBody @Valid AgenceDTO agenceDTO , HttpServletRequest request){
        try{
        	//get Admin Username who created this agence
			String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
        	Claims claims = Jwts.parser()
					.setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
					.getBody();
				String username = claims.getSubject();
       
			Agence agence = new Agence();
			BeanUtils.copyProperties(agenceDTO, agence);
			agence.setVille(villeRepository.findByNom(agenceDTO.getVille()));
		    agence.setAdmin(adminRepository.findByUsername(username));
			agence = agenceRepository.save(agence);
			
			agenceDTO.setId(agence.getId());
			agenceDTO.setAdmin(username);
        	return agenceDTO;
        
        }catch (Exception e) {
            throw new RuntimeException("Valeur en double détectée pour le libelle d'agence (NOM)!");
        }
    }

    /**
     *
     * @param id
     * @param AgenceDTO
     * @return
     */
    @RequestMapping(value = "/updateAgence", method = RequestMethod.POST)
    public Agence updateAgence(@RequestBody @Valid AgenceDTO updatedAgence){

        Agence oldAgence = agenceRepository.findAgenceById(updatedAgence.getId());

        BeanUtils.copyProperties(updatedAgence,oldAgence);
        oldAgence.setVille(villeRepository.findByNom(updatedAgence.getVille()));
        oldAgence.setAdmin(adminRepository.findByUsername(updatedAgence.getAdmin()));

            try{
                return agenceRepository.save(oldAgence);
            }catch (Exception e) { throw new RuntimeException("Valeur en double détectée pour le libelle d'agence (NOM)!"); }

        
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteAgence/{id}", method = RequestMethod.POST)
    public Agent deleteAgence(@PathVariable int id){

        Agence agence = agenceRepository.findAgenceById(id);

        if (agence != null) {
            try{
            	agenceRepository.delete(agence);
            }catch (Exception e) {
                throw new RuntimeException("Cet Agence a deja des agents !");
			}
        }
        else
            throw new RuntimeException("No Agence found with id(" + id + ") !");

        return null;
    }



   // CRUD CategorieService ::
   
   /**
   *
   * 
   * @return
   */
   
   @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
   public List<CategorieServiceDTO> getAllSCatervices(){
   	List<CategorieServiceDTO> categorieDtos = new ArrayList<>();
   	
   	List<CategorieService>categories=categorieServiceRepos.findAll();
   	
   	for(CategorieService categorie : categories)
   	{
   		CategorieServiceDTO categorieDto = new CategorieServiceDTO();
   		BeanUtils.copyProperties(categorie,categorieDto);    
   		categorieDtos.add(categorieDto);
   	}
   	return categorieDtos;
   }
   

   /**
   *
   * @param CategorieServiceDTO
   * @return
   */
  @RequestMapping(value = "/addNewCategorie", method = RequestMethod.POST)
  public CategorieService addCategorie(@RequestBody @Valid CategorieServiceDTO categorieDTO){
      try{
    
			CategorieService categorie = new CategorieService();
			BeanUtils.copyProperties(categorieDTO, categorie);
			return categorieServiceRepos.save(categorie);
			
			            
      }catch (Exception e) {
          throw new RuntimeException("Valeur en double détectée pour le libelle du categorie (description)!");
      }
  }


  /**
  *
  * @param CategorieServiceDTO
  * @return
  */
  @RequestMapping(value = "/updateCategorie", method = RequestMethod.POST)
  public CategorieService updateCategorieService(@RequestBody @Valid CategorieServiceDTO updatedCategorie){

      CategorieService oldCategorie = (categorieServiceRepos.findById(updatedCategorie.getId())).get();
      
      BeanUtils.copyProperties(updatedCategorie,oldCategorie);
          try{
              return categorieServiceRepos.save(oldCategorie);
          }catch (Exception e) { throw new RuntimeException("Valeur en double détectée pour le libelle du categorie (description)!"); }

      
  }
  
  /**
  *
  * @param id
  * 
  */

  @RequestMapping(value = "/deleteCategoroe/{id}", method = RequestMethod.POST)
  public void deleteCat(@PathVariable int id){

      CategorieService categorie = (categorieServiceRepos.findById(id)).get();

      if (categorie != null)
      {
   	   try {
   	    categorieServiceRepos.delete(categorie);
         }catch(Exception e) {
       	  throw new RuntimeException("Cette categorie a deja des sous Categorie !");  
         }
      
      }
      
      else 
          throw new RuntimeException("No Agence found with id(" + id + ") !");

      
  }

  

  // CRUD SousCategorieService ::
  
  /**
  *
  * 
  * @return
  */
  
  @RequestMapping(value = "/getSousCategories", method = RequestMethod.GET)
  public List<SousCategorieDTO> getAllSousCatervices(){
  	List<SousCategorieDTO> categorieDtos = new ArrayList<>();
  	
  	List<SousCategorieService>categories=sousCategorieServiceRepos.findAll();
  	
  	for(SousCategorieService categorie : categories)
  	{
  		SousCategorieDTO categorieDto = new SousCategorieDTO();
  		categorieDto.setCategorie(categorie.getCategorie().getDescription());
  		BeanUtils.copyProperties(categorie,categorieDto);    
  		categorieDtos.add(categorieDto);
  	}
  	return categorieDtos;
  }
  

  /**
  *
  * @param SousCategorieServiceDTO
  * @return
  */
 @RequestMapping(value = "/addNewSousCategorie", method = RequestMethod.POST)
 public SousCategorieService addSousCategorie(@RequestBody @Valid SousCategorieDTO categorieDTO){
     try{
   
			SousCategorieService categorie = new SousCategorieService();
			CategorieService cat = categorieServiceRepos.findByDescription(categorieDTO.getCategorie());
			BeanUtils.copyProperties(categorieDTO, categorie);
			categorie.setCategorie(cat);
			return sousCategorieServiceRepos.save(categorie);
			
			            
     }catch (Exception e) {
         throw new RuntimeException("Valeur en double détectée pour le libelle du categorie (description)!");
     }
 }


 /**
 *
 * @param SousCategorieServiceDTO
 * @return
 */
 @RequestMapping(value = "/updateSousCategorie", method = RequestMethod.POST)
 public SousCategorieService updateSousCategorieService(@RequestBody @Valid SousCategorieDTO updatedCategorie){

     SousCategorieService oldCategorie = sousCategorieServiceRepos.findById(updatedCategorie.getId());
     CategorieService cat = categorieServiceRepos.findByDescription(updatedCategorie.getCategorie());
     BeanUtils.copyProperties(updatedCategorie,oldCategorie);
     oldCategorie.setCategorie(cat);
         try{
             return sousCategorieServiceRepos.save(oldCategorie);
         }catch (Exception e) { throw new RuntimeException("Valeur en double détectée pour le libelle du categorie (description)!"); }

     
 }
 
 /**
 *
 * @param id
 * 
 */

 @RequestMapping(value = "/deleteSousCategoroe/{id}", method = RequestMethod.POST)
 public void deleteSousCat(@PathVariable int id){

     SousCategorieService categorie = sousCategorieServiceRepos.findById(id);

     if (categorie != null)
     {
  	   try {
  	    sousCategorieServiceRepos.delete(categorie);
        }catch(Exception e) {
      	  throw new RuntimeException("Cette categorie a deja des sous Categorie !");  
        }
     
     }
     
     else 
         throw new RuntimeException("No SousCategorie found with id(" + id + ") !");

     
 }



   
}
