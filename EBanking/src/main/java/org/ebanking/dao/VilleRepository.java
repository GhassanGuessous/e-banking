package org.ebanking.dao;

import org.ebanking.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, Integer>{

    public Ville findVilleById(Integer id);
    public Ville findByNom(String nom);
}
