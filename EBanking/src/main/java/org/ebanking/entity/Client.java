package org.ebanking.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Client extends UserMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codePostal;
	
	@ManyToOne
    @JsonBackReference(value="agentClient")
	private Agent agent;
	
	@OneToMany(mappedBy = "client")
	@JsonBackReference
	private List<Reclamation> reclamations;
	
	@OneToMany(mappedBy = "client")
	@JsonBackReference(value = "clientCompte")
	private List<Compte> comptes;
	
	// limite virement par journée
	private double limite;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String nom, String prenom, String adresse, String telephone, String email, String username,
			String password, String cin, boolean activated, Role role, int codePostal, Agent agent) {
		super(nom, prenom, adresse, telephone, email, username, password, cin, activated, role);
		this.codePostal = codePostal;
		this.agent = agent;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
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

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
	

	
	
	
	
	

}
