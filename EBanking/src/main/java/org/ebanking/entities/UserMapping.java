package org.ebanking.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class UserMapping extends User{

    public UserMapping(String nom, String prenom, String adresse, Long telephone, String email, String username, String password, String cin, boolean activated) {
        super(nom, prenom, adresse, telephone, email, username, password, cin, activated);
    }

    public UserMapping() {}
}
