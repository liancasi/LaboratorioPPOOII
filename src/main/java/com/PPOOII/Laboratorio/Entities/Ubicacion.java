package com.PPOOII.Laboratorio.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Ubicacion", schema = "ppooii")
public class Ubicacion {

    @Id
    private int id;

    @Column(name = "Lugar")
    private String lugar;
    
    @Column(name = "Ciudad")
    private String ciudad;
    
    @Column(name = "Departamento")
    private String departamento;
    
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Persona persona;
    
    // Constructores
    public Ubicacion() {}
    
    public Ubicacion(String lugar, String ciudad, String departamento) {
        this.lugar = lugar;
        this.ciudad = ciudad;
        this.departamento = departamento;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    // toString method
    @Override
    public String toString() {
        return "Ubicacion [id=" + id + ", lugar=" + lugar + ", ciudad=" + ciudad + ", departamento=" + departamento + "]";
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ubicacion)) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return getId() == ubicacion.getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}