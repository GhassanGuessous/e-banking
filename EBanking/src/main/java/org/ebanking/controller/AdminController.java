package org.ebanking.controller;

import org.ebanking.dao.*;
import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;
import org.ebanking.entity.Agent;
import org.ebanking.entity.Role;
import org.ebanking.web.inputs.AgenceInput;
import org.ebanking.web.inputs.AgentInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    private PasswordEncoder passwordEncoder;

    /**
     *
     * @param agentInput
     * @return
     */
    @RequestMapping(value = "/addNewAgent", method = RequestMethod.POST)
    public Agent addAgent(@RequestBody @Valid AgentInput agentInput){

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
                agenceRepository.findAgenceById(agentInput.getAgence()),
                adminRepository.findAdminById(agentInput.getAdmin()),
                roleRepository.findRoleByRole("ROLE_AGENT")
        ));
    }

    /**
     *
     * @param id
     * @param newAgentInput
     * @return
     */
    @RequestMapping(value = "/updateAgent/{id}", method = RequestMethod.POST)
    public Agent updateAgent(@PathVariable int id, @RequestBody @Valid AgentInput newAgentInput){

        Agent oldAgent = agentRepository.findAgentById(id);

        if (oldAgent != null) {
            oldAgent.setNom(newAgentInput.getNom());
            oldAgent.setPrenom(newAgentInput.getPrenom());
            oldAgent.setAdresse(newAgentInput.getAdresse());
            oldAgent.setTelephone(newAgentInput.getTelephone());
            oldAgent.setEmail(newAgentInput.getEmail());
            oldAgent.setUsername(newAgentInput.getUsername());
            oldAgent.setPassword(passwordEncoder.encode(newAgentInput.getPassword()));
            oldAgent.setCin(newAgentInput.getCin());
            oldAgent.setActivated(newAgentInput.isActivated());
            oldAgent.setAgence(agenceRepository.findAgenceById(newAgentInput.getAgence()));
            oldAgent.setAdmin(adminRepository.findAdminById(newAgentInput.getAdmin()));
            oldAgent.setRole(roleRepository.findRoleByRole("ROLE_AGENT"));

            return agentRepository.save(oldAgent);
        }

        else
            throw new RuntimeException("No Agent found with id(" + id + ") !");
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteAgent/{id}", method = RequestMethod.POST)
    public Agent deleteAgent(@PathVariable int id){

        Agent agent = agentRepository.findAgentById(id);

        if (agent != null)
             agentRepository.delete(agent);
        else
            throw new RuntimeException("No Agent found with id(" + id + ") !");

        return null;
    }


    /**
     *
     * @return
     */
    @RequestMapping(value = "/getAgents", method = RequestMethod.GET)
    public List<Agent> getAllAgents(){
        return agentRepository.findAll();
    }


    // CRUD Agences ::

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getAgences", method = RequestMethod.GET)
    public List<Agence> getAllAgences(){
        return agenceRepository.findAll();
    }

    /**
     *
     * @param agenceInput
     * @return
     */
    @RequestMapping(value = "/addNewAgence", method = RequestMethod.POST)
    public Agence addAgence(@RequestBody @Valid AgenceInput agenceInput){

        return agenceRepository.save(new Agence(
                agenceInput.getNom(),
                agenceInput.getAdresse(),
                villeRepository.findVilleById(agenceInput.getVille()),
                adminRepository.findAdminById(agenceInput.getAdmin())
        ));
    }

    /**
     *
     * @param id
     * @param newAgenceInput
     * @return
     */
    @RequestMapping(value = "/updateAgence/{id}", method = RequestMethod.POST)
    public Agence updateAgence(@PathVariable int id, @RequestBody @Valid AgenceInput newAgenceInput){

        Agence oldAgence = agenceRepository.findAgenceById(id);

        if (oldAgence != null) {
            oldAgence.setNom(newAgenceInput.getNom());
            oldAgence.setAdresse(newAgenceInput.getAdresse());
            oldAgence.setVille(villeRepository.findVilleById(newAgenceInput.getVille()));
            oldAgence.setAdmin(adminRepository.findAdminById(newAgenceInput.getAdmin()));

            return agenceRepository.save(oldAgence);
        }

        else
            throw new RuntimeException("No Agence found with id(" + id + ") !");
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
