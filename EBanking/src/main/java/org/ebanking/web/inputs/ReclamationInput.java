package org.ebanking.web.inputs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@Getter
public class ReclamationInput {

    @NotNull(message = "Vous devez saisir le corps de votre réclamation")
    private String corps;
}
