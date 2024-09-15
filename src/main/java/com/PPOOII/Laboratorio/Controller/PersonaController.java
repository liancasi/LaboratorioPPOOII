package com.PPOOII.Laboratorio.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Services.PersonaServiceImpl;
import com.PPOOII.Laboratorio.Services.Interfaces.IPersonaService;

@RestController
@RequestMapping("/Laboratorio1")
public class PersonaController {
	
	// ==========INYECCION DEL SERVICE==========
	@Autowired
	@Qualifier("PersonaService")
	private PersonaServiceImpl personaService;
	
	// ==========MÉTODOS HTTP====================
	// METODO POST
	@PostMapping("/persona")
	public boolean agregarPersona(@RequestBody @Validated Persona persona) {
		return personaService.guardar(persona);
	}
	// MÉTODO PUT
	@PutMapping("/persona")
	public boolean editarPersona(@RequestBody @Validated Persona persona) {
		return personaService.actualizar(persona);

	}
	// MÉTODO DELETE
	@DeleteMapping("/persona/{id}")
	public boolean eliminarPersona(@PathVariable("id") int id) {
		return personaService.eliminar(id);
	}
	// MÉTODO GET
	@GetMapping("/personas")
	public List<Persona> listadoPersona(Pageable pageable) {
		return personaService.consultarPersona(pageable);
	}

	// ==============MÉTODOS HTTP DE BÚSQUEDA =============
	// ---GET---
	@GetMapping("/persona/id/{id}")
	public Persona getById(@PathVariable("id") int id) {
		return personaService.findById(id);
	}
	// ---GET---
	@GetMapping("/persona/pnombre/{pnombre}")
	public List<Persona> getByPnombre(@PathVariable("pnombre") String pnombre) {
		return personaService.findByPnombre(pnombre);
	}
	// ---GET---
	@GetMapping("/persona/edad/{edad}")
	public List<Persona> getByEdad(@PathVariable("edad") int edad) {
		return personaService.findByEdad(edad);
	}
	
	public Persona getByIdentificacion(@PathVariable("identificacion") int identificacion) {
        return personaService.findByIdentificacion(identificacion);
    }

   
    @GetMapping("/persona/snombre/{snombre}")
    public List<Persona> getBySNombre(@PathVariable("snombre") String snombre) {
        return personaService.findBySNombre(snombre);
    }

    @GetMapping("/persona/papellido/{papellido}")
    public List<Persona> getByPApellido(@PathVariable("papellido") String papellido) {
        return personaService.findByPApellido(papellido);
    }

    @GetMapping("/persona/sapellido/{sapellido}")
    public List<Persona> getBySApellido(@PathVariable("sapellido") String sapellido) {
        return personaService.findBySApellido(sapellido);
    }

    @GetMapping("/persona/email/{email}")
    public List<Persona> getByEmail(@PathVariable("email") String email) {
        return personaService.findByEmail(email);
    }

    @GetMapping("/persona/fechanacimiento/{fechaNacimiento}")
    public List<Persona> getByFechaNacimiento(@PathVariable("fechaNacimiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento) {
        return personaService.findByFechaNacimiento(fechaNacimiento);
    }


    @GetMapping("/persona/edadclinica/{edadClinica}")
    public List<Persona> getByEdadClinica(@PathVariable("edadClinica") String edadClinica) {
        return personaService.findByEdadClinica(edadClinica);
    }
}

