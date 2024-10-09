package com.PPOOII.Laboratorio.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Services.Interfaces.IPersonaService;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/registro")
public class RegisterController {

	private IPersonaService personaService;
	
	public RegisterController (IPersonaService personaService) {
		super();
		this.personaService = personaService;
	}

	@ModelAttribute("persona")
	public Persona retornarNuevoUsuario() {
		return new Persona();
	}
	
    @GetMapping
    public String mostrarFormularioDeRegistro() {
                return "Registro";
    }

    @PostMapping
    public String registrarUsuario(@ModelAttribute("persona") Persona persona) {
    	personaService.guardar(persona);
    	return "redirect:/registro?exito";
 
    }
}