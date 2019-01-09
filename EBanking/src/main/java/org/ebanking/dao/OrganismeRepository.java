package org.ebanking.dao;

import org.ebanking.entity.Organisme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganismeRepository extends JpaRepository<Organisme, Integer> {

	public Organisme findById(int id);
}
