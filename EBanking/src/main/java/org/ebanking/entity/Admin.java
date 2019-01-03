package org.ebanking.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Admin extends UserMapping implements Serializable {

	@OneToMany(mappedBy = "admin")
	@JsonBackReference
	private List<Agence> agences;
	@OneToMany(mappedBy = "admin")
	@JsonBackReference
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
