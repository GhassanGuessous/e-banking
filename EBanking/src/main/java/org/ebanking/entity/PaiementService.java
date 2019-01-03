package org.ebanking.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class PaiementService implements Serializable {

	/**
	 * il faut ajouter encore plus d'attributs
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Long numeroContrat;
	private Long numeroTelephone;
	private double montant;
	@ManyToOne
	@JsonManagedReference
	private SousCategorieService sousCategorieService;
	@ManyToOne
	@JsonManagedReference
	private Compte compte;
	
	public PaiementService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaiementService(Long numeroContrat, Long numeroTelephone, double montant,
			SousCategorieService sousCategorieService, Compte compte) {
		super();
		this.numeroContrat = numeroContrat;
		this.numeroTelephone = numeroTelephone;
		this.montant = montant;
		this.sousCategorieService = sousCategorieService;
		this.compte = compte;
	}

	public Long getNumeroContrat() {
		return numeroContrat;
	}

	public void setNumeroContrat(Long numeroContrat) {
		this.numeroContrat = numeroContrat;
	}

	public Long getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(Long numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public SousCategorieService getSousCategorieService() {
		return sousCategorieService;
	}

	public void setSousCategorieService(SousCategorieService sousCategorieService) {
		this.sousCategorieService = sousCategorieService;
	}
	
	
}
