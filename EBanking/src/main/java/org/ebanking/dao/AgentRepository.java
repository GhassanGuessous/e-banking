package org.ebanking.dao;

import org.ebanking.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Integer> {

	public Agent findByUsername(String username);
	public Agent findAgentById(Integer id);
}
