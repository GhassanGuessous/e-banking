package org.ebanking.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Reclamation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String corps;
	private String etat;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDepot;
	
	@ManyToOne
    @JsonBackReference(value="clientReclamation")
	private Client client;
	
	@ManyToOne
    @JsonBackReference(value="agentReclamation")
	private Agent agent;
	
	public Reclamation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reclamation(String corps, String etat, Client client, Agent agent, Date dateDepot) {
		super();
		this.corps = corps;
		this.etat = etat;
		this.client = client;
		this.agent = agent;
		this.dateDepot = dateDepot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorps() {
		return corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}
	
	
}
