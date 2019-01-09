package org.ebanking.web.inputs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@Getter
public class VirementInput {

    @NotNull(message = "Vous devez saisir le rib du compte source")
    private Long ribSource;

    @NotNull(message = "Vous devez saisir le rib du compte source")
    private Long ribDestination;

    @NotNull(message = "Vous devez saisir le montant")
    private double montant;
}
