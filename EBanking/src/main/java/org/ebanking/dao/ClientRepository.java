package org.ebanking.dao;

import org.ebanking.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	public Client findByUsername(String username);
	
}