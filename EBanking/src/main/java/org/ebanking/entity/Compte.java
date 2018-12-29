package org.ebanking.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Compte implements Serializable {

	@Id
	private Long rib;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	@ManyToOne
	private TypeCompte type;
	private double sold;
	@ManyToOne
	private Client client;
	@ManyToOne
	private Agent agent;
	@OneToMany(mappedBy = "compteSource")
	private List<Virement> virementsEnvoyes;
	@OneToMany(mappedBy = "compteDestination")
	private List<Virement> virementsRecus;
	@OneToMany(mappedBy = "compte")
	private List<PaiementService> paiementServices;
	
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compte(Long rib, Date dateCreation, TypeCompte type, double sold, Client client, Agent agent) {
		super();
		this.rib = rib;
		this.dateCreation = dateCreation;
		this.type = type;
		this.sold = sold;
		this.client = client;
		this.agent = agent;
	}

	public Long getRib() {
		return rib;
	}

	public void setRib(Long rib) {
		this.rib = rib;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public TypeCompte getType() {
		return type;
	}

	public void setType(TypeCompte type) {
		this.type = type;
	}

	public double getSold() {
		return sold;
	}

	public void setSold(double sold) {
		this.sold = sold;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public List<Virement> getVirementsEnvoyes() {
		return virementsEnvoyes;
	}

	public void setVirementsEnvoyes(List<Virement> virementsEnvoyes) {
		this.virementsEnvoyes = virementsEnvoyes;
	}

	public List<Virement> getVirementsRecus() {
		return virementsRecus;
	}

	public void setVirementsRecus(List<Virement> virementsRecus) {
		this.virementsRecus = virementsRecus;
	}

	public List<PaiementService> getPaiementServices() {
		return paiementServices;
	}

	public void setPaiementServices(List<PaiementService> paiementServices) {
		this.paiementServices = paiementServices;
	}
	
	
	
}
