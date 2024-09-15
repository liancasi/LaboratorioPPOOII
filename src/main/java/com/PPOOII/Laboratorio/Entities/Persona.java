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

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	public int id;

	@Column(name = "PNOMBRE", nullable = false)
	private String pnombre;

	@Column(name = "EDAD")
	private int edad;

	@Column(name = "ubicacion")
	private String ubicacion;

	@Column(name = "IDENTIFICACION")
	private int identificacion;

	@Column(name = "SNOMBRE")
	private String SNombre;

	@Column(name = "PAPELLIDO")
	private String PApellido;

	@Column(name = "SAPELLIDO")
	private String SApellido;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "FECHANACIMIENTO")
	private LocalDate fechaNacimiento;

	@Column(name = "EDADCLINICA")
	private String edadClinica;
	
	@JsonManagedReference
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;


	public Persona(int id, String pnombre, int edad, String ubicacion, int identificacion, String pNombre2,
			String sNombre, String pApellido, String sApellido, String email, LocalDate fechaNacimiento,
			String edadClinica) {
		this.id = id;
		this.pnombre = pnombre;
		this.edad = edad;
		this.ubicacion = ubicacion;

	}

	public Persona() {
	}

	public Persona(String pnombre, int Edad, String ubicacion, int identificacion, String pNombre2, String sNombre,
			String pApellido, String sApellido, String email, LocalDate fechaNacimiento, String edadClinica) {
		super();
		this.pnombre = pnombre;
		this.edad = Edad;
		this.ubicacion = ubicacion;
		this.identificacion = identificacion;
		this.SNombre = sNombre;
		this.PApellido = pApellido;
		this.SApellido = sApellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.edadClinica = edadClinica;
	}

	public Persona(int Id, String pnombre, int Edad, String ubicacion) {
		super();
		this.id = Id;
		this.pnombre = pnombre;
		this.edad = Edad;
		this.ubicacion = ubicacion;
	}

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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Persona [id=" + this.id + ", Primer Nombre=" + this.pnombre + ", Edad=" + this.edad + ", Ubicacion: "
				+ this.ubicacion + "]";
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

	public void setSNombre(String sNombre) {
		SNombre = sNombre;
	}

	public String getPApellido() {
		return PApellido;
	}

	public void setPApellido(String pApellido) {
		PApellido = pApellido;
	}

	public String getSApellido() {
		return SApellido;
	}

	public void setSApellido(String sApellido) {
		SApellido = sApellido;
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
	}

	public String getEdadClinica() {
		return edadClinica;
	}

	public void setEdadClinica(String edadClinica) {
		this.edadClinica = edadClinica;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
