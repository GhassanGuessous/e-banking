package org.ebanking.controller;

import org.ebanking.dao.RoleRepository;
import org.ebanking.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@Secured(value = {"ROLE_ADMIN"})
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping(value = "/addRole")
    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    @RequestMapping(value = "/getAllRoles")
    public List<Role> getAll(){
        return roleRepository.findAll();
    }
}
