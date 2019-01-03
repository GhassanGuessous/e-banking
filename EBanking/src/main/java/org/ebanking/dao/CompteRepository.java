package org.ebanking.dao;

import java.util.List;

import org.ebanking.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompteRepository extends JpaRepository<Compte, Long> {

	public Compte findByRib(Long rib);
	
	@Query("select c from Compte c where c.client.id = :id")
	public List<Compte> findByClientId(@Param("id")int id);
}
