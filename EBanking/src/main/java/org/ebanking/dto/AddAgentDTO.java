package org.ebanking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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


	@Override
	public String toString() {
		return "AgentDTO [id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse
				+ ", telephone=" + telephone + ", email=" + email + ", username=" + username + ", activated="
				+ activated + ", agence=" + agence + ", admin=" + admin + "]";
	}

	
}
