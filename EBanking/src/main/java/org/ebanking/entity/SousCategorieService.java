package org.ebanking.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class SousCategorieService implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	@ManyToOne
    @JsonBackReference(value="catSous")
	private CategorieService categorie;
	
	@OneToMany(mappedBy = "sousCategorieService")
    @JsonManagedReference(value="sousPaiement")
	private List<PaiementService> paiementServices;
	
	public SousCategorieService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SousCategorieService(String description, CategorieService categorie) {
		super();
		this.description = description;
		this.categorie = categorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategorieService getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieService categorie) {
		this.categorie = categorie;
	}

	public List<PaiementService> getPaiementServices() {
		return paiementServices;
	}

	public void setPaiementServices(List<PaiementService> paiementServices) {
		this.paiementServices = paiementServices;
	}
	
	
}
