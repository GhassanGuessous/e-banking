package org.ebanking.dto;

public class SousCategorieDTO {

	private int id;
	private String description;
	private String categorie;
	
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
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public SousCategorieDTO(int id, String description, String categorie) {
		super();
		this.id = id;
		this.description = description;
		this.categorie = categorie;
	}
	
	public SousCategorieDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
