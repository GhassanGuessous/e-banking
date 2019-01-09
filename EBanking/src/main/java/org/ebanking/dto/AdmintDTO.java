package org.ebanking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.ebanking.entity.Admin;
import org.ebanking.entity.Agence;

public class AdmintDTO {

	private int id;
	private String username;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public AdmintDTO(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	public AdmintDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
