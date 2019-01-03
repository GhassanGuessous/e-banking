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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Virement implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JsonManagedReference
	private Compte compteSource;
	@ManyToOne
	@JsonManagedReference
	private Compte compteDestination;
	private double montant;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateVirement;
	
	public Virement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Virement(Compte compteSource, Compte compteDestination, double montant, Date dateVirement) {
		super();
		this.compteSource = compteSource;
		this.compteDestination = compteDestination;
		this.montant = montant;
		this.dateVirement = dateVirement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Compte getCompteSource() {
		return compteSource;
	}

	public void setCompteSource(Compte compteSource) {
		this.compteSource = compteSource;
	}

	public Compte getCompteDestination() {
		return compteDestination;
	}

	public void setCompteDestination(Compte compteDestination) {
		this.compteDestination = compteDestination;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDateVirement() {
		return dateVirement;
	}

	public void setDateVirement(Date dateVirement) {
		this.dateVirement = dateVirement;
	}

	@Override
	public String toString() {
		return "Virement [compteSource=" + compteSource + ", compteDestination=" + compteDestination + ", montant="
				+ montant + ", dateVirement=" + dateVirement + "]";
	}
	
	
}
