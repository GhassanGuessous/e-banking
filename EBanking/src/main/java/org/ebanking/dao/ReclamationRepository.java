package org.ebanking.dao;

import java.util.List;

import org.ebanking.entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
	public List<Reclamation> findByClientId(int id);
	public List<Reclamation> findByAgentId(int agent_id);
	public Reclamation findById(int id);
	public List<Reclamation> findByVerifie(boolean etat);


}
