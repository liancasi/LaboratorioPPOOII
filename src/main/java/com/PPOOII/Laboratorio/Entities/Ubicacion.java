package com.PPOOII.Laboratorio.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ubicacion", schema = "ppooii")
public class Ubicacion {

	@Column(name = "Lugar")
	private String lugar;
	
	@Column(name = "Ciudad")
	private String ciudad;
	
	@Column(name = "Departamento")
	private String departamento;
	
	@OneToOne
	private Persona persona;
	
	public Ubicacion() {}
	
	public Ubicacion(String lugar, String ciudad, String departamento, Persona persona) {
		this.lugar = lugar;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.persona = persona;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	
	
	

	
	
}
