package org.ebanking.controller;

import org.ebanking.dao.AdminRepository;
import org.ebanking.dao.AgentRepository;
import org.ebanking.entity.Admin;
import org.ebanking.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(value = "/Admin")
//@Secured(value = {"ROLE_ADMIN"})
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AgentRepository agentRepository;

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
     *
     */
    @RequestMapping(value = "/addAgent")
    public Agent addAgent(Agent agent){
        return agentRepository.save(agent);
    }

    @RequestMapping(value = "/getAllAgents")
    public List<Agent> getAllAgents(){
        return agentRepository.findAll();
    }
}
