package org.ebanking.dao;

import org.ebanking.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	public Admin findByUsername(String username);
}
