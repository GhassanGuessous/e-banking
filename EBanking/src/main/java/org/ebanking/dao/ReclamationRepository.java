package org.ebanking.dao;

import java.util.List;

import org.ebanking.entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
	public List<Reclamation> findByClientId(int id);
}
