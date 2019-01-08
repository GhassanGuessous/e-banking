package org.ebanking.controller;

import org.ebanking.dao.AdminRepository;
import org.ebanking.dao.AgenceRepository;
import org.ebanking.dao.AgentRepository;
import org.ebanking.dto.AgentDTO;
import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;
import org.ebanking.entity.Agent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/Admin")
//@Secured(value = {"ROLE_ADMIN"})
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AgenceRepository ageenceRepository;


    
    @GetMapping(value="/tokenTest")
    public String testToken()
    {
    	return "succes";
    }
    
    @RequestMapping(value = "/addAdmin")
    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    @RequestMapping(value = "/getAllAdmins")
    public List<Admin> getAll(){
        return adminRepository.findAll();
    }

    /**
     *
     */
    @RequestMapping(value = "/addAgent")
    public Agent addAgent(Agent agent){
        return agentRepository.save(agent);
    }

//    @RequestMapping(value = "/getAllAgents")
//    public List<Agent> getAllAgents(){
//        return agentRepository.findAll();
//    	 }
    
    @RequestMapping(value = "/getAllAgents")
    public List<AgentDTO> getAllAgents(){
        
        List<AgentDTO> agentdtos = new ArrayList<>();
        
        List<Agent> agents = agentRepository.findAll();
        
        for(Agent agent : agents ) {
        	System.out.println(agent.getUsername());
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
       // return agentRepository.findAll();
    	//List<Agent> agents = agentRepository.;

        AgentDTO agentDTO = new AgentDTO();
        
    	Agent agent = agentRepository.findByUsername(username);
    	
    	BeanUtils.copyProperties(agent,agentDTO);
    	agentDTO.setAgence(agent.getAgence().getNom());
    	agentDTO.setAdmin(agent.getAdmin().getUsername());
		//comptedto.setClient(c.getClient());
    	
    	return  agentDTO;
    }
    

    @RequestMapping(value = "/updateAgent",method=RequestMethod.POST)
    public Agent UpdateAgent(@RequestBody AgentDTO agentDTO){
       // return agentRepository.findAll();
    	//List<Agent> agents = agentRepository.;

    	Optional<Agent> opagent = agentRepository.findById(agentDTO.getId());
    	Agent agent = opagent.get();   	
    	BeanUtils.copyProperties(agentDTO,agent);
    	
    	Admin admin =adminRepository.findByUsername(agentDTO.getAdmin());
    	Agence agence = ageenceRepository.findByNom(agentDTO.getAgence());
    	agent.setAgence(agence);
    	agent.setAdmin(admin);
    	
    	agentRepository.save(agent);
		//comptedto.setClient(c.getClient());
    	
    	return  agent;
    }
    
    
    
}
