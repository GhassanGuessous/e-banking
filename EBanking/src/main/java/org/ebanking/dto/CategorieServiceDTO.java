package org.ebanking.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategorieServiceDTO {

	private int id;
	@NotNull(message = "description is a required field")
	@Size(min = 2, max = 25, message = "description cannot be less than 2 and not longer than 15 characters")
	private String description;
	
	
}
