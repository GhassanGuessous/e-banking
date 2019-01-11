package org.ebanking.web.inputs;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
@Getter
public class AgentInput {

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

    @NotNull(message = "PASSWORD is a required field")
    @Size(min=8, max=20, message = "PASSWORD cannot be less than 8 and not longer than 20 characters")
    private String password;

    @NotNull(message = "ACTIVATED is a required field")
    private boolean activated;

    @NotNull(message = "AGENCE is a required field")
    private int agence;

    @NotNull(message = "ADMIN is a required field")
    private int admin;

	
}
