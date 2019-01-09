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

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public int getOrganisme() {
		return organisme;
	}

	public void setOrganisme(int organisme) {
		this.organisme = organisme;
	}

	public Long getCompte() {
		return compte;
	}

	public void setCompte(Long compte) {
		this.compte = compte;
	}

	public DonInput(@NotNull(message = "Vous devez saisir le montant du don") double montant, @NotNull int organisme,
			@NotNull Long compte) {
		super();
		this.montant = montant;
		this.organisme = organisme;
		this.compte = compte;
	}

	public DonInput() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
