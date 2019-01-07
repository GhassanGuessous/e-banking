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
import org.springframework.web.bind.annotation.*;

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
}
