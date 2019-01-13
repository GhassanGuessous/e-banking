package org.ebanking.web.inputs;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.ebanking.entity.Agent;

import lombok.Data;
import lombok.Getter;
@Data
@Getter
public class ClientInputByAgent extends ClientInput implements Serializable {
	@NotNull(message = "Agent is a required field")
	private Agent agent;
	

}
