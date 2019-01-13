package org.ebanking.web.inputs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@Getter
public class PaiementServiceInput {

    @NotNull(message = "Vous devez saisir le montant")
    private double montant;

    private Long numeroContrat;

    private Long numeroTelephone;
    
    @NotNull(message = "Vous devez saisir le rib")
    @Digits(integer = 16, fraction = 0)
    private Long compte;
    
    @NotNull(message = "Vous devez saisir quel service vous voulez payer")
    private int sousCategorie;
    
    @NotNull(message = "Vous devez saisir l'organisme")
    private int organisme;
    
    
}
