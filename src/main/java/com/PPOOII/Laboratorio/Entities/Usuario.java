package com.PPOOII.Laboratorio.Entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity(name = "UsrANDPer")
@Table(schema = "ppooii", name = "usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UsuarioPK id;
	@Column(name = "password")
	private String password;
	@Column(name = "apikey")
	private String apikey;
	
	@JsonBackReference
	@OneToOne
	@MapsId
	@JoinColumn(name = "IDPERSONA")
	private Persona persona;

	
	
	public Usuario() {}
	public Usuario(String login, String password) {
		this.id.setLogin(login);
		this.password = password;
	}

	public UsuarioPK getId() {
		return id;
	}
	public void setId(UsuarioPK id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}