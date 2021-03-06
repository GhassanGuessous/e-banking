
//PACKAGE ORG.EBANKING.CONTROLLER;
//
//IMPORT ORG.EBANKING.DAO.*;
//IMPORT ORG.EBANKING.ENTITY.ADMIN;
//IMPORT ORG.EBANKING.ENTITY.AGENCE;
//IMPORT ORG.EBANKING.ENTITY.AGENT;
//IMPORT ORG.EBANKING.ENTITY.ROLE;
//IMPORT ORG.EBANKING.WEB.INPUTS.AGENCEINPUT;
//IMPORT ORG.EBANKING.WEB.INPUTS.AGENTINPUT;
//IMPORT ORG.SPRINGFRAMEWORK.BEANS.FACTORY.ANNOTATION.AUTOWIRED;
//IMPORT ORG.SPRINGFRAMEWORK.SECURITY.CRYPTO.PASSWORD.PASSWORDENCODER;
//IMPORT ORG.SPRINGFRAMEWORK.WEB.BIND.ANNOTATION.*;
//
//IMPORT JAVAX.VALIDATION.VALID;
//IMPORT JAVA.UTIL.LIST;
//
//@RESTCONTROLLER()
//@REQUESTMAPPING(VALUE = "/ADMIN")
////@SECURED(VALUE = {"ROLE_ADMIN"})
//PUBLIC CLASS ADMINCONTROLLER {
//
//    @AUTOWIRED
//    PRIVATE VILLEREPOSITORY VILLEREPOSITORY;
//    @AUTOWIRED
//    PRIVATE ROLEREPOSITORY ROLEREPOSITORY;
//    @AUTOWIRED
//    PRIVATE AGENCEREPOSITORY AGENCEREPOSITORY;
//    @AUTOWIRED
//    PRIVATE ADMINREPOSITORY ADMINREPOSITORY;
//    @AUTOWIRED
//    PRIVATE AGENTREPOSITORY AGENTREPOSITORY;
//    @AUTOWIRED
//    PRIVATE PASSWORDENCODER PASSWORDENCODER;
//
//    /**
//     *
//     * @PARAM AGENTINPUT
//     * @RETURN
//     */
//    @REQUESTMAPPING(VALUE = "/ADDNEWAGENT", METHOD = REQUESTMETHOD.POST)
//    PUBLIC AGENT ADDAGENT(@REQUESTBODY @VALID AGENTINPUT AGENTINPUT){
//        TRY {
//            RETURN AGENTREPOSITORY.SAVE(NEW AGENT(
//                    AGENTINPUT.GETNOM(),
//                    AGENTINPUT.GETPRENOM(),
//                    AGENTINPUT.GETADRESSE(),
//                    AGENTINPUT.GETTELEPHONE(),
//                    AGENTINPUT.GETEMAIL(),
//                    AGENTINPUT.GETUSERNAME(),
//                    PASSWORDENCODER.ENCODE(AGENTINPUT.GETPASSWORD()),
//                    AGENTINPUT.GETCIN(),
//                    AGENTINPUT.ISACTIVATED(),
//                    AGENCEREPOSITORY.FINDAGENCEBYID(AGENTINPUT.GETAGENCE()),
//                    ADMINREPOSITORY.FINDADMINBYID(AGENTINPUT.GETADMIN()),
//                    ROLEREPOSITORY.FINDROLEBYROLE("ROLE_AGENT")
//            ));
//        }CATCH (EXCEPTION E) {
//            THROW NEW RUNTIMEEXCEPTION("VALEUR EN DOUBLE DÉTECTÉE POUR LES CHAMPS EMAIL OU USERNAME !");
//        }
//    }
//
//    /**
//     *
//     * @PARAM ID
//     * @PARAM NEWAGENTINPUT
//     * @RETURN
//     */
//    @REQUESTMAPPING(VALUE = "/UPDATEAGENT/{ID}", METHOD = REQUESTMETHOD.POST)
//    PUBLIC AGENT UPDATEAGENT(@PATHVARIABLE INT ID, @REQUESTBODY @VALID AGENTINPUT NEWAGENTINPUT){
//
//        AGENT OLDAGENT = AGENTREPOSITORY.FINDAGENTBYID(ID);
//
//        IF (OLDAGENT != NULL) {
//            OLDAGENT.SETNOM(NEWAGENTINPUT.GETNOM());
//            OLDAGENT.SETPRENOM(NEWAGENTINPUT.GETPRENOM());
//            OLDAGENT.SETADRESSE(NEWAGENTINPUT.GETADRESSE());
//            OLDAGENT.SETTELEPHONE(NEWAGENTINPUT.GETTELEPHONE());
//            OLDAGENT.SETEMAIL(NEWAGENTINPUT.GETEMAIL());
//            OLDAGENT.SETUSERNAME(NEWAGENTINPUT.GETUSERNAME());
//            OLDAGENT.SETPASSWORD(PASSWORDENCODER.ENCODE(NEWAGENTINPUT.GETPASSWORD()));
//            OLDAGENT.SETCIN(NEWAGENTINPUT.GETCIN());
//            OLDAGENT.SETACTIVATED(NEWAGENTINPUT.ISACTIVATED());
//            OLDAGENT.SETAGENCE(AGENCEREPOSITORY.FINDAGENCEBYID(NEWAGENTINPUT.GETAGENCE()));
//            OLDAGENT.SETADMIN(ADMINREPOSITORY.FINDADMINBYID(NEWAGENTINPUT.GETADMIN()));
//            OLDAGENT.SETROLE(ROLEREPOSITORY.FINDROLEBYROLE("ROLE_AGENT"));
//
//            TRY {
//                RETURN AGENTREPOSITORY.SAVE(OLDAGENT);
//            }CATCH (EXCEPTION E) { THROW NEW RUNTIMEEXCEPTION("VALEUR EN DOUBLE DÉTECTÉE POUR LES CHAMPS EMAIL OU USERNAME !"); }
//        }
//
//        ELSE
//            THROW NEW RUNTIMEEXCEPTION("NO AGENT FOUND WITH ID(" + ID + ") !");
//    }
//
//    /**
//     *
//     * @PARAM ID
//     * @RETURN
//     */
//    @REQUESTMAPPING(VALUE = "/DELETEAGENT/{ID}", METHOD = REQUESTMETHOD.POST)
//    PUBLIC AGENT DELETEAGENT(@PATHVARIABLE INT ID){
//
//        AGENT AGENT = AGENTREPOSITORY.FINDAGENTBYID(ID);
//
//        IF (AGENT != NULL)
//             AGENTREPOSITORY.DELETE(AGENT);
//        ELSE
//            THROW NEW RUNTIMEEXCEPTION("NO AGENT FOUND WITH ID(" + ID + ") !");
//
//        RETURN NULL;
//    }
//
//
//    /**
//     *
//     * @RETURN
//     */
//    @REQUESTMAPPING(VALUE = "/GETAGENTS", METHOD = REQUESTMETHOD.GET)
//    PUBLIC LIST<AGENT> GETALLAGENTS(){
//        RETURN AGENTREPOSITORY.FINDALL();
//    }
//
//
//    // CRUD AGENCES ::
//
//    /**
//     *
//     * @RETURN
//     */
//    @REQUESTMAPPING(VALUE = "/GETAGENCES", METHOD = REQUESTMETHOD.GET)
//    PUBLIC LIST<AGENCE> GETALLAGENCES(){
//        RETURN AGENCEREPOSITORY.FINDALL();
//    }
//
//    /**
//     *
//     * @PARAM AGENCEINPUT
//     * @RETURN
//     */
//    @REQUESTMAPPING(VALUE = "/ADDNEWAGENCE", METHOD = REQUESTMETHOD.POST)
//    PUBLIC AGENCE ADDAGENCE(@REQUESTBODY @VALID AGENCEINPUT AGENCEINPUT){
//        TRY{
//            RETURN AGENCEREPOSITORY.SAVE(NEW AGENCE(
//                    AGENCEINPUT.GETNOM(),
//                    AGENCEINPUT.GETADRESSE(),
//                    VILLEREPOSITORY.FINDVILLEBYID(AGENCEINPUT.GETVILLE()),
//                    ADMINREPOSITORY.FINDADMINBYID(AGENCEINPUT.GETADMIN())
//            ));
//        }CATCH (EXCEPTION E) {
//            THROW NEW RUNTIMEEXCEPTION("VALEUR EN DOUBLE DÉTECTÉE POUR LE LIBELLE D'AGENCE (NOM)!");
//        }
//    }
//
//    /**
//     *
//     * @PARAM ID
//     * @PARAM NEWAGENCEINPUT
//     * @RETURN
//     */
//    @REQUESTMAPPING(VALUE = "/UPDATEAGENCE/{ID}", METHOD = REQUESTMETHOD.POST)
//    PUBLIC AGENCE UPDATEAGENCE(@PATHVARIABLE INT ID, @REQUESTBODY @VALID AGENCEINPUT NEWAGENCEINPUT){
//
//        AGENCE OLDAGENCE = AGENCEREPOSITORY.FINDAGENCEBYID(ID);
//
//        IF (OLDAGENCE != NULL) {
//            OLDAGENCE.SETNOM(NEWAGENCEINPUT.GETNOM());
//            OLDAGENCE.SETADRESSE(NEWAGENCEINPUT.GETADRESSE());
//            OLDAGENCE.SETVILLE(VILLEREPOSITORY.FINDVILLEBYID(NEWAGENCEINPUT.GETVILLE()));
//            OLDAGENCE.SETADMIN(ADMINREPOSITORY.FINDADMINBYID(NEWAGENCEINPUT.GETADMIN()));
//
//            TRY{
//                RETURN AGENCEREPOSITORY.SAVE(OLDAGENCE);
//            }CATCH (EXCEPTION E) { THROW NEW RUNTIMEEXCEPTION("VALEUR EN DOUBLE DÉTECTÉE POUR LE LIBELLE D'AGENCE (NOM)!"); }
//        }
//
//        ELSE
//            THROW NEW RUNTIMEEXCEPTION("NO AGENCE FOUND WITH ID(" + ID + ") !");
//    }
//
//    /**
//     *
//     * @PARAM ID
//     * @RETURN
//     */
//    @REQUESTMAPPING(VALUE = "/DELETEAGENCE/{ID}", METHOD = REQUESTMETHOD.POST)
//    PUBLIC AGENT DELETEAGENCE(@PATHVARIABLE INT ID){
//
//        AGENCE AGENCE = AGENCEREPOSITORY.FINDAGENCEBYID(ID);
//
//        IF (AGENCE != NULL)
//            AGENCEREPOSITORY.DELETE(AGENCE);
//        ELSE
//            THROW NEW RUNTIMEEXCEPTION("NO AGENCE FOUND WITH ID(" + ID + ") !");
//
//        RETURN NULL;
//    }
//
//
//
//
//
//}
package org.ebanking.controller;

import org.ebanking.dto.AddAgentDTO;
import org.ebanking.dto.AdmintDTO;
import org.ebanking.dto.AgenceDTO;
import org.ebanking.dto.AgentDTO;
import org.ebanking.dto.CategorieServiceDTO;
import org.ebanking.dto.ClientDTO;
import org.ebanking.dto.SousCategorieDTO;
import org.ebanking.dto.TypeCompteDTO;
import org.ebanking.dao.*;

import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;
import org.ebanking.entity.Agent;
import org.ebanking.entity.CategorieService;
import org.ebanking.entity.Client;
import org.ebanking.entity.Organisme;
import org.springframework.beans.BeanUtils;

import org.ebanking.entity.Role;
import org.ebanking.entity.SousCategorieService;
import org.ebanking.entity.TypeCompte;
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
    @Autowired
 	private TypeCompteRepository typeCompteRepository;
    @Autowired
 	private OrganismeRepository organismeRepository;
   

    
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


 
 // CRUD TypeCompte ::
 
 /**
 *
 * 
 * @return
 */
 
 @RequestMapping(value = "/getTypeComptes", method = RequestMethod.GET)
 public List<TypeCompteDTO> getAlltypeComptes(){
 	List<TypeCompteDTO> typeCompteDTOs = new ArrayList<>();
 	
 	List<TypeCompte>types=typeCompteRepository.findAll();
 	
 	for(TypeCompte type : types)
 	{
 		TypeCompteDTO typeDto = new TypeCompteDTO();
 		BeanUtils.copyProperties(type,typeDto);    
 		typeCompteDTOs.add(typeDto);
 	}
 	return typeCompteDTOs;
 }
 

 /**
 *
 * @param TypeCompte
 * @return
 */
@RequestMapping(value = "/addNewTypeCompte", method = RequestMethod.POST)
public TypeCompte addTypeCompte(@RequestBody @Valid TypeCompteDTO typeDto){
    try{
  
    		TypeCompte type = new TypeCompte();
			BeanUtils.copyProperties(typeDto, type);
			return typeCompteRepository.save(type);
			
			            
    }catch (Exception e) {
        throw new RuntimeException("Valeur en double détectée pour le libelle du categorie (description)!");
    }
}


/**
*
* @param SousCategorieServiceDTO
* @return
*/
@RequestMapping(value = "/updateTypeCompte", method = RequestMethod.POST)
public TypeCompte updateTypeCompte(@RequestBody @Valid TypeCompteDTO updatedtypeCompte){

	TypeCompte oldTypeCompte = typeCompteRepository.findById(updatedtypeCompte.getId());
    BeanUtils.copyProperties(updatedtypeCompte,oldTypeCompte);
        try{
            return typeCompteRepository.save(oldTypeCompte);
        }catch (Exception e) { throw new RuntimeException("Valeur en double détectée pour le libelle du categorie (description)!"); }

    
}

/**
*
* @param id
* 
*/

@RequestMapping(value = "/deleteTypeCompte/{id}", method = RequestMethod.POST)
public void deletetTypeCompte(@PathVariable int id){

    TypeCompte type = typeCompteRepository.findById(id);

    if (type != null)
    {
 	   try {
 		  typeCompteRepository.delete(type);
       }catch(Exception e) {
     	  throw new RuntimeException("Ce Type a deja des Comptes !");  
       }
    
    }
    
    else 
        throw new RuntimeException("No TypeCompte found with id(" + id + ") !");

    
}



@RequestMapping(value = "/getOrganisms", method = RequestMethod.GET)
public List<Organisme> getAllOrganisme(){
	
	return  organismeRepository.findAll();
	
}


/**
*
* @param TypeCompte
* @return
*/
@RequestMapping(value = "/addOrganisme", method = RequestMethod.POST)
public Organisme addOrganisme(@RequestBody Organisme neworganisme){
   try{
   		return organismeRepository.save(neworganisme);		            
   }catch (Exception e) {
       throw new RuntimeException("Valeur en double détectée pour le libelle du Organisme (nom)!");
   }
}


/**
*
* @param SousCategorieServiceDTO
* @return
*/
@RequestMapping(value = "/updateOrganisme", method = RequestMethod.POST)
public Organisme updateOrganisme(@RequestBody Organisme updatedOrganisme){


       try{
           return organismeRepository.save(updatedOrganisme);
       }catch (Exception e) { throw new RuntimeException("Valeur en double détectée pour le libelle du Organisme (nom)!"); }

   
}

/**
*
* @param id
* 
*/

@RequestMapping(value = "/deleteOrganisme/{id}", method = RequestMethod.POST)
public void deleteOrgnaisme(@PathVariable int id){

	Organisme organisme = organismeRepository.findById(id);

   if (organisme != null)
   {
	   try {
		   organismeRepository.delete(organisme);
      }catch(Exception e) {
    	  throw new RuntimeException("Ce Type a deja des Organismes !");  
      }
   
   }
   
   else 
       throw new RuntimeException("No Organismes found with id(" + id + ") !");

   
}



   
}
