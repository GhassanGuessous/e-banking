package org.ebanking.dto;

public class CategorieServiceDTO {

	private int id;
	private String description;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public CategorieServiceDTO(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	public CategorieServiceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
