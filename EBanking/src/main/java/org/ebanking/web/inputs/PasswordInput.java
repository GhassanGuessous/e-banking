package org.ebanking.web.inputs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
@Getter
public class PasswordInput {

    @NotNull(message = "Vous devez saisir l'ancien mot de passe")
    @Size(min=8, max=20, message = "PASSWORD cannot be less than 8 and not longer than 20 characters")
    private String oldPassword;
    
    @NotNull(message = "Vous devez saisir le nouveau mot de passe")
    @Size(min=8, max=20, message = "PASSWORD cannot be less than 8 and not longer than 20 characters")
    private String newPassword;
    
    @NotNull(message = "Vous devez saisir confirmer le mot de pass")
    @Size(min=8, max=20, message = "PASSWORD cannot be less than 8 and not longer than 20 characters")
    private String confirmedPassword;
    
    
}
