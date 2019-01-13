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
    
    @NotNull()
    private int client;

	public String getCorps() {
		return corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public ReclamationInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReclamationInput(@NotNull(message = "Vous devez saisir le corps de votre réclamation") String corps,
			@NotNull int client) {
		super();
		this.corps = corps;
		this.client = client;
	}
    
    
}
