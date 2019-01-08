package org.ebanking.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Admin extends UserMapping implements Serializable {

	@OneToMany(mappedBy = "admin")
	@JsonManagedReference(value="adminAgence")
	private List<Agence> agences;
	
	@OneToMany(mappedBy = "admin")
	@JsonManagedReference(value="adminAgent")
	private List<Agent> agents;
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Admin(String nom, String prenom, String adresse, String telephone, String email, String username, String password, String cin, boolean activated,
			List<Agence> agences, List<Agent> agents , Role role) 
	{
        super(nom, prenom, adresse, telephone, email, username, password, cin, activated,role);
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
