package org.ebanking.web.inputs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@Getter
public class VirementInput {

    @NotNull(message = "Vous devez saisir le rib du compte source")
    @Digits(integer = 16, fraction = 0)
    private Long ribSource;

    @NotNull(message = "Vous devez saisir le rib du compte source")
    @Digits(integer = 16, fraction = 0)
    private Long ribDestination;

    @NotNull(message = "Vous devez saisir le montant")
    private double montant;

	public Long getRibSource() {
		return ribSource;
	}

	public void setRibSource(Long ribSource) {
		this.ribSource = ribSource;
	}

	public Long getRibDestination() {
		return ribDestination;
	}

	public void setRibDestination(Long ribDestination) {
		this.ribDestination = ribDestination;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public VirementInput(@NotNull(message = "Vous devez saisir le rib du compte source") Long ribSource,
			@NotNull(message = "Vous devez saisir le rib du compte source") Long ribDestination,
			@NotNull(message = "Vous devez saisir le montant") double montant) {
		super();
		this.ribSource = ribSource;
		this.ribDestination = ribDestination;
		this.montant = montant;
	}

	public VirementInput() {
		super();
		// TODO Auto-generated constructor stub
	}
    	
    
}
