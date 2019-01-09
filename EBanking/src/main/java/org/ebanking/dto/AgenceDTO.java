package org.ebanking.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AgenceDTO {

	
	private int id;
	@NotNull(message = "nom is a required field")
	@Size(min = 6, max = 50, message = "NOM cannot be less than 6 and not longer than 50 characters")
	private String nom;
	@NotNull(message = "adresse is a required field")
	@Size(min = 6, max = 50, message = "Addresse cannot be less than 6 and not longer than 50 characters")
	private String adresse;
	@NotNull(message = "ville is a required field")
	private String ville;
	
	private String admin;
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
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public AgenceDTO(int id, String nom, String adresse, String ville, String admin) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.ville = ville;
		this.admin = admin;
	}
	
	public AgenceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
