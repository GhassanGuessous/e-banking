/**
 * 
 */
package org.ebanking.web.inputs;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;

/**
 * @author Ghassan
 *
 */
@Data
@Getter
public class ClientInput  implements Serializable {
	@NotNull(message = "CIN is a required field")
    @Size(min=6, max=10, message = "CIN cannot be less than 6 and not longer than 10 characters")
    private String cin;

    @NotNull(message = "NOM is a required field")
    @Size(min=3, max=50, message = "NOM cannot be less than 3 and not longer than 50 characters")
    private String nom;

    @NotNull(message = "PRENOM is a required field")
    @Size(min=3, max=60, message = "PRENOM cannot be less than 3 and not longer than 60 characters")
    private String prenom;

    @NotNull(message = "ADDRESS is a required field")
    @Size(min=10, max=200, message = "ADDRESS cannot be less than 10 and not longer than 200 characters")
    private String adresse;

    @NotNull(message = "TELEPHONE is a required field")
    @Size(min=10, max=10, message = "TELEPHONE must be in 10 characters")
    private String telephone;

    @NotNull(message = "EMAIL is a required field")
    @Email
    private String email;

    @NotNull(message = "USERNAME is a required field")
    @Size(min=5, message = "USERNAME cannot be less than 10 characters")
    private String username;


    @NotNull(message = "CODE POSTAL is a required field")
    private int codePostal;
	 @NotNull(message = "CONFIRMED PASSWORD is a required field")
	    @Size(min=8, max=20, message = "PASSWORD cannot be less than 8 and not longer than 20 characters")
	    private String confirmedPassword;
	    
	    @NotNull(message = "PASSWORD is a required field")
	    @Size(min=8, max=20, message = "PASSWORD cannot be less than 8 and not longer than 20 characters")
	    private String password;
}
