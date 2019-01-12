package org.ebanking.dao;

import org.ebanking.entity.CategorieService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieServiceRepository extends JpaRepository<CategorieService,Integer> {

	public CategorieService findByDescription(String description);
}
