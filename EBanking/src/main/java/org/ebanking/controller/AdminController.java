package org.ebanking.controller;

import org.ebanking.dao.AdminRepository;
import org.ebanking.dao.AgenceRepository;
import org.ebanking.dao.AgentRepository;
import org.ebanking.dto.AddAgentDTO;
import org.ebanking.dto.AdmintDTO;
import org.ebanking.dto.AgenceDTO;
import org.ebanking.dto.AgentDTO;

import org.ebanking.dao.*;

import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;
import org.ebanking.entity.Agent;

import org.springframework.beans.BeanUtils;

import org.ebanking.entity.Role;
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
    /**
     *
     * @param agentInput
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
     * @param id
     * @param newAgentInput
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

        if (agent != null)
             agentRepository.delete(agent);
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
     * @param agenceInput
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
     * @param newAgenceInput
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

        if (agence != null)
            agenceRepository.delete(agence);
        else
            throw new RuntimeException("No Agence found with id(" + id + ") !");

        return null;
    }






}
