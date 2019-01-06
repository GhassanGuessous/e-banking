package org.ebanking.controller;

import org.ebanking.dao.AdminRepository;
import org.ebanking.dao.AgenceRepository;
import org.ebanking.dao.AgentRepository;
import org.ebanking.dao.RoleRepository;
import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;
import org.ebanking.entity.Agent;
import org.ebanking.entity.Role;
import org.ebanking.web.inputs.AgentInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping(value = "/Admin")
//@Secured(value = {"ROLE_ADMIN"})
public class AdminController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     *
     */
    @RequestMapping(value = "/addAdmin")
    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    @RequestMapping(value = "/getAllAdmins")
    public List<Admin> getAll(){
        return adminRepository.findAll();
    }

    /**
     *	Admin admin , Role role)
     * @param agentInput
     * @return
     */
    @RequestMapping(value = "/addNewAgent", method = RequestMethod.POST)
    public Agent addAgent(@RequestBody @Valid AgentInput agentInput){

        Role agentRole = roleRepository.getOne("ROLE_AGENT");
        System.out.println("FOUND :: " + agentRole);
        Agence agentAgence = agenceRepository.getOne(agentInput.getAgence());
        Admin agentAdmin = adminRepository.getOne(agentInput.getAdmin());

        return agentRepository.save(new Agent(
                agentInput.getNom(),
                agentInput.getPrenom(),
                agentInput.getAdresse(),
                agentInput.getTelephone(),
                agentInput.getEmail(),
                agentInput.getUsername(),
                passwordEncoder.encode(agentInput.getPassword()),
                agentInput.getCin(),
                agentInput.isActivated(),
                agentAgence,
                agentAdmin,
                agentRole
        ));
    }

    @RequestMapping(value = "/getAllAgents")
    public List<Agent> getAllAgents(){
        return agentRepository.findAll();
    }
}
