package org.ebanking.dao;

import java.util.List;

import org.ebanking.entity.Agent;
import org.ebanking.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	public Client findByUsername(String username);
	public Client findById(int id);
	public List<Client> findByActivated(boolean activated);
	public List<Client> findByAgent(Agent agent);
	
}