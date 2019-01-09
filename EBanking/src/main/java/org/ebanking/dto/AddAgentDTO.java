package org.ebanking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;

public class AddAgentDTO {


	private String password;
	
	private int id;
	@NotNull(message = "CIN is a required field")
	@Size(min = 6, max = 10, message = "CIN cannot be less than 6 and not longer than 10 characters")
	private String cin;
	
	@NotNull(message = "NOM is a required field")
	@Size(min = 3, max = 30, message = "NOM cannot be less than 3 and not longer than 50 characters")
	private String nom;
	
	@NotNull(message = "PRENOM is a required field")
	@Size(min = 3, max = 30, message = "PRENOM cannot be less than 3 and not longer than 50 characters")
	private String prenom;
	
	@NotNull(message = "ADRESSE is a required field")
	@Size(min = 3, max = 50, message = "ADRESSE cannot be less than 3 and not longer than 50 characters")
	private String adresse;
	
	@NotNull(message = "TELEPHONE is a required field")
	@Size(min = 2, max = 50, message = "TELEPHONE cannot be less than 3 and not longer than 50 characters")
	private String telephone;
	
	@NotNull(message = "EMAIL is a required field")
	@Email
	private String email;
	
	private String username;
	@NotNull
	private boolean activated;

	private String agence;
	private String admin;

	
	
	
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCin() {
		return cin;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getAgence() {
		return agence;
	}

	public void setAgence(String agence) {
		this.agence = agence;
	}

	public AddAgentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddAgentDTO(int id, String cin, String nom, String prenom, String adresse, String telephone, String email,
			String username, boolean activated , String password) {
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.username = username;
		this.activated = activated;
		this.password=password;

	}

	@Override
	public String toString() {
		return "AgentDTO [id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse
				+ ", telephone=" + telephone + ", email=" + email + ", username=" + username + ", activated="
				+ activated + ", agence=" + agence + ", admin=" + admin + "]";
	}

	
}
