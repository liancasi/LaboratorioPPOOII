package com.PPOOII.Laboratorio.Entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Persona", schema = "ppooii")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPersona")
    private int id;

    @Column(name = "PrimerNombre", nullable = false)
    private String pnombre;

    @Column(name = "EDAD")
    private int edad;

    @Column(name = "Identificacion")
    private int identificacion;

    @Column(name = "SegundoNombre")
    private String SNombre;

    @Column(name = "PrimerApellido")
    private String PApellido;

    @Column(name = "SegundoApellido")
    private String SApellido;

    @Column(name = "Email")
    private String email;

    @Column(name = "FechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "EdadClinica")
    private String edadClinica;
    
    @JsonManagedReference
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ubicacion ubicacion;

    @JsonManagedReference
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

    // Constructores
    public Persona() {
    }

    public Persona(String pnombre, int identificacion, String SNombre, String PApellido, String SApellido, String email, LocalDate fechaNacimiento) {
        this.pnombre = pnombre;
        this.identificacion = identificacion;
        this.SNombre = SNombre;
        this.PApellido = PApellido;
        this.SApellido = SApellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        calcularEdad();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPnombre() {
        return pnombre;
    }

    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getSNombre() {
        return SNombre;
    }

    public void setSNombre(String SNombre) {
        this.SNombre = SNombre;
    }

    public String getPApellido() {
        return PApellido;
    }

    public void setPApellido(String PApellido) {
        this.PApellido = PApellido;
    }

    public String getSApellido() {
        return SApellido;
    }

    public void setSApellido(String SApellido) {
        this.SApellido = SApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        calcularEdad();
    }

    public String getEdadClinica() {
        return edadClinica;
    }

    public void setEdadClinica(String edadClinica) {
        this.edadClinica = edadClinica;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
        if (ubicacion != null) {
            ubicacion.setPersona(this);
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null) {
            usuario.setPersona(this);
        }
    }

    // Métodos adicionales
    @PrePersist
    @PreUpdate
    private void calcularEdad() {
        if (fechaNacimiento != null) {
            LocalDate hoy = LocalDate.now();
            Period periodo = Period.between(fechaNacimiento, hoy);
            
            this.edad = periodo.getYears();
            this.edadClinica = String.format("%d años %d meses %d días", 
                                             periodo.getYears(), 
                                             periodo.getMonths(), 
                                             periodo.getDays());
        }
    }

    @Override
    public String toString() {
        return "Persona [id=" + id + ", pnombre=" + pnombre + ", edad=" + edad + ", identificacion=" + identificacion
                + ", SNombre=" + SNombre + ", PApellido=" + PApellido + ", SApellido=" + SApellido + ", email=" + email
                + ", fechaNacimiento=" + fechaNacimiento + ", edadClinica=" + edadClinica + "]";
    }
}