package org.ebanking.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CategorieService implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	@OneToMany(mappedBy = "categorie")
	private List<SousCategorieService> sousCategorie;
	
	public CategorieService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategorieService(String description, List<SousCategorieService> sousCategorie) {
		super();
		this.description = description;
		this.sousCategorie = sousCategorie;
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

	public List<SousCategorieService> getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(List<SousCategorieService> sousCategorie) {
		this.sousCategorie = sousCategorie;
	}
	
	
	
}
