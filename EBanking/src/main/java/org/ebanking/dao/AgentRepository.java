package org.ebanking.dao;

import org.ebanking.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
