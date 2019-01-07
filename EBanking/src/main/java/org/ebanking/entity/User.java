package org.ebanking.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"email"}), @UniqueConstraint(columnNames={"username"})})
public abstract class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String cin;
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private String password;
	private boolean activated;
	private String email;
	private String username;
	
	@ManyToOne
    @JsonBackReference(value="roleUser")
	private Role role;
	
	public User() {
		super();
	}

	public User(String nom, String prenom, String adresse, String telephone, String email, String username,
			String password, String cin, boolean activated , Role role) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.activated = activated;
		this.role=role;
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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", cin='" + cin + '\'' +
				", nom='" + nom + '\'' +
				", prenom='" + prenom + '\'' +
				", adresse='" + adresse + '\'' +
				", telephone='" + telephone + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", activated=" + activated +
				'}';
	}
}
