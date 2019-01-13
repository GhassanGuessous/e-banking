package org.ebanking.dao;

import org.ebanking.entity.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeCompteRepository extends JpaRepository<TypeCompte,Integer>{

	public TypeCompte findById(int id);
}
