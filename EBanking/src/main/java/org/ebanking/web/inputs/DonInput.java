package org.ebanking.web.inputs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@Getter
public class DonInput {

    @NotNull(message = "Vous devez saisir le montant du don")
    private double montant;

    @NotNull()
    private int organisme;
    
    @NotNull()
    private Long compte;
}
