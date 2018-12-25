package org.ebanking.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Agent extends User implements Serializable {

	@OneToOne
	private Agence agence;
	@ManyToOne
	private Admin admin;
	@OneToMany(mappedBy = "agent")
	private List<Client> clients;
	@OneToMany(mappedBy = "agent")
	private List<Reclamation> reclamations;
	@OneToMany(mappedBy = "agent")
	private List<Compte> comptes;
	
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agent(String nom, String prenom, String adresse, Long telephone, String email, String username,
			String password, String cin, Agence agence, Admin admin) {
		super(nom, prenom, adresse, telephone, email, username, password, cin);
		this.agence = agence;
		this.admin = admin;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Reclamation> getReclamations() {
		return reclamations;
	}

	public void setReclamations(List<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
	

	

	
	
	
	
}
