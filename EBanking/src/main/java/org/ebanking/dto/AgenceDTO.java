package org.ebanking.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
		
}
