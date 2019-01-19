package org.ebanking;

import static org.junit.Assert.assertEquals;

import org.ebanking.controller.AdminController;
import org.ebanking.dao.AdminRepository;
import org.ebanking.dao.AgenceRepository;
import org.ebanking.dao.AgentRepository;
import org.ebanking.dao.RoleRepository;
import org.ebanking.dto.AddAgentDTO;
import org.ebanking.dto.AgentDTO;
import org.ebanking.entity.Agent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTests {

	AdminController adminController = new AdminController();
	
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
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void AddAgentTest() {
		
		//Agent to Add
		AddAgentDTO agentDTO = new AddAgentDTO();
		agentDTO.setActivated(true);
		agentDTO.setAdmin("admin1");
		agentDTO.setAdresse("addresse Aveneu 19");
		agentDTO.setAgence("Sidi bouzid");
		agentDTO.setCin("BB437293");
		agentDTO.setEmail("Agent200@gmail.com");
		agentDTO.setNom("nom");
		agentDTO.setPrenom("prenom");
		agentDTO.setUsername("Agent200");
		agentDTO.setTelephone("0612584920");
		agentDTO.setPassword("test12345");
		
		//Expected Agent added
		Agent agent = new Agent();
    	BeanUtils.copyProperties(agentDTO,agent);
    	agent.setPassword(passwordEncoder.encode(agent.getPassword()));
    	agent.setAgence(ageenceRepository.findByNom(agentDTO.getAgence()));
    	agent.setAdmin(adminRepository.findByUsername(agentDTO.getAdmin()));
    	agent.setRole(roleRepository.findRoleByRole("ROLE_AGENT"));
        

    	assertEquals(agent,adminController.addAgent(agentDTO));
		
	}

}

