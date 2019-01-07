package org.ebanking.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Agent extends UserMapping implements Serializable {

	@OneToOne
	private Agence agence;
	
	@ManyToOne
    @JsonBackReference(value="adminAgent")
	private Admin admin;
	
	@OneToMany(mappedBy = "agent")
    @JsonManagedReference(value="agentClient")
	private List<Client> clients;
	
	@OneToMany(mappedBy = "agent")
    @JsonManagedReference(value="agentReclamation")
	private List<Reclamation> reclamations;
	
	@OneToMany(mappedBy = "agent")
    @JsonManagedReference(value="agentCompte")
	private List<Compte> comptes;
	
	public Agent() {
		super();
	}
	
	public Agent(String nom, String prenom, String adresse, String telephone, String email, String username,
			String password, String cin, boolean activated, Agence agence, Admin admin , Role role) {
		super(nom, prenom, adresse, telephone, email, username, password, cin, activated , role);
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

	@Override
	public String toString() {
		return "Agent{" +
				"agence=" + agence +
				", admin=" + admin +
				", clients=" + clients +
				", reclamations=" + reclamations +
				", comptes=" + comptes +
				'}';
	}
}
