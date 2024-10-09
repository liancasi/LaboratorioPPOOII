package com.PPOOII.Laboratorio.Entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Embeddable
public class UsuarioPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "Login")
	private String login;
	
	@Column(name = "persona")
	private int persona;
	
	public UsuarioPK () {}
	
	public UsuarioPK (String login, int persona) {
		this.login = login;
		this.persona = persona;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getPersona() {
		return persona;
	}

	public void setPersona(int persona) {
		this.persona = persona;
	}
	
}
