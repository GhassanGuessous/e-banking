package org.ebanking.web.inputs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
@Getter
public class AgenceInput {

    @NotNull(message = "LIBELLE is a required field")
    @Size(min = 5, max = 200, message = "LIBELLE cannot be less than 5 and not longer than 200 characters")
    private String nom;

    @NotNull(message = "ADDRESS is a required field")
    @Size(min = 10, max = 200, message = "ADDRESS cannot be less than 10 and not longer than 200 characters")
    private String adresse;

    @NotNull(message = "VILLE is a required field")
    private Integer ville;

    @NotNull(message = "ADMIN is a required field")
    private Integer admin;
}
