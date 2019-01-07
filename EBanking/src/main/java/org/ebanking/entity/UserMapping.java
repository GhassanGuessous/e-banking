package org.ebanking.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class UserMapping extends User{

    public UserMapping(String nom, String prenom, String adresse, String telephone, String email, String username, String password, String cin, boolean activated,Role role) {
        super(nom, prenom, adresse, telephone, email, username, password, cin, activated,role);
    }

    public UserMapping() {}
}
