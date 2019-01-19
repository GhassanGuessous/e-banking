package org.ebanking;

import static org.junit.Assert.assertEquals;

import org.ebanking.controller.AdminController;
import org.ebanking.controller.ClientController;
import org.ebanking.dao.AdminRepository;
import org.ebanking.dao.AgenceRepository;
import org.ebanking.dao.AgentRepository;
import org.ebanking.dao.RoleRepository;
import org.ebanking.dto.AddAgentDTO;
import org.ebanking.dto.AgentDTO;
import org.ebanking.entity.Agent;
import org.ebanking.entity.Client;
import org.ebanking.web.inputs.ClientInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTests {

	ClientController clientController = new ClientController();
	
	 @Autowired
	    private RoleRepository roleRepository;
	 @Autowired   
	 private PasswordEncoder passwordEncoder;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void substituteClientTest() {
		
		
		//Agent to Add
		
		ClientInput client = new ClientInput();
		client.setCin("BB147422");
		client.setPrenom("Prenom");
		client.setNom("NomCli");
		client.setAdresse("Addressse");
		client.setEmail("Emailcli@gmail@com");
		client.setCodePostal(12313);
		client.setTelephone("052343412");
		client.setUsername("Client200");
		client.setPassword(passwordEncoder.encode("test123456789"));
		client.setConfirmedPassword(passwordEncoder.encode("test123456789"));
		

		//Expected Client
		Client clientexp = new Client();
		clientexp.setCin("BB147422");
		clientexp.setPrenom("Prenom");
		clientexp.setNom("NomCli");
		clientexp.setAdresse("Addressse");
		clientexp.setEmail("Emailcli@gmail@com");
		clientexp.setCodePostal(12313);
		clientexp.setTelephone("052343412");
		clientexp.setUsername("Client200");
		clientexp.setPassword(passwordEncoder.encode("test123456789"));
		clientexp.setRole(roleRepository.findRoleByRole("ROLE_CLIENT"));
		clientexp.setActivated(false);

		

    	assertEquals(clientexp,clientController.substitute(client));
		
	}

}

