package com.PPOOII.Laboratorio.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Entities.UsuarioPK;
import com.PPOOII.Laboratorio.Services.PersonaServiceImpl;
import com.PPOOII.Laboratorio.Services.UsuarioServiceImpl;
import com.PPOOII.Laboratorio.Services.Interfaces.IPersonaService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Laboratorio1")
public class PersonaController {
	
	// ==========INYECCION DEL SERVICE==========
	@Autowired
	@Qualifier("PersonaService")
	private PersonaServiceImpl personaService;
	@Autowired
	@Qualifier("UsuarioService")
	private UsuarioServiceImpl usuarioService;
	
	
	// ==========MÉTODOS HTTP====================
	// METODO POST
	@PostMapping("/persona")
	public ResponseEntity<String> agregarPersona(@RequestBody @Validated Persona persona) {
	    if (usuarioService.existsByUsername(persona.getUsuario().getLogin())) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("El nombre de usuario ya está registrado: " + persona.getUsuario().getLogin());
	    }

	    if (personaService.existsByEmail(persona.getEmail())) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está registrado: " + persona.getEmail());
	    }

	    if (personaService.existsByIdentificacion(persona.getIdentificacion())) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("La identificación ya está registrada: " + persona.getIdentificacion());
	    }

	    boolean isSaved = personaService.guardar(persona);
	    if (isSaved) {
	        return ResponseEntity.status(HttpStatus.CREATED).body("Persona registrada exitosamente.");
	    } else {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al registrar la persona.");
	    }
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

	@PostMapping("/personass")
	public ResponseEntity<?> validarUsuario(@RequestBody Map<String, String> credentials) {
	    String login = credentials.get("login");
	    String password = credentials.get("password");
	    
	    System.out.println("Login recibido: " + login);
	    System.out.println("Password recibido: " + password);
	    
	    Usuario usuario = usuarioService.findByUsername(login);
	    if (usuario != null && BCrypt.checkpw(password, usuario.getPassword())) {
	        return ResponseEntity.ok("Usuario autenticado");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
	    }
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
	
	@GetMapping("/persona/identificacion/{identificacion}")
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

