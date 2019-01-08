package org.ebanking.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
public class Ville implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	
	@OneToMany(mappedBy = "ville")
    @JsonManagedReference(value="villeAgence")
	private List<Agence> agences;
	
	public Ville() {
		super();
	}
	public Ville(String nom) {
		super();
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Agence> getAgences() {
		return agences;
	}
	public void setAgences(List<Agence> agences) {
		this.agences = agences;
	}
	
	
	
}
