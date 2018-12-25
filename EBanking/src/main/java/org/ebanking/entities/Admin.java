package org.ebanking.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Admin extends User implements Serializable {

	@OneToMany(mappedBy = "admin")
	private List<Agence> agences;
	@OneToMany(mappedBy = "admin")
	private List<Agent> agents;
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Admin(List<Agence> agences, List<Agent> agents) {
		super();
		this.agences = agences;
		this.agents = agents;
	}

	public List<Agence> getAgences() {
		return agences;
	}

	public void setAgences(List<Agence> agences) {
		this.agences = agences;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
	
	

	

	
}
