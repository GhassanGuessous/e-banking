package org.ebanking.dao;

import org.ebanking.entity.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirementRepository extends JpaRepository<Virement, Integer> {

}
