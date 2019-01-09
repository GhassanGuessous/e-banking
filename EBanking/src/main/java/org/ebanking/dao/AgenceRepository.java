package org.ebanking.dao;

import org.ebanking.entity.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceRepository extends JpaRepository<Agence, Integer>{

	public Agence findByNom(String nom);
    public Agence findAgenceById(int id);
	
}
