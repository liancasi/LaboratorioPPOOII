package com.PPOOII.Laboratorio.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Repository.PersonaRepository;
import com.PPOOII.Laboratorio.Repository.UsuarioRepository;
import com.PPOOII.Laboratorio.Services.Interfaces.IPersonaService;
import com.PPOOII.Laboratorio.Services.Interfaces.IUsuarioService;

import org.apache.logging.log4j.Logger;

@Service("PersonaService")
public class PersonaServiceImpl implements IPersonaService{
    // INYECCIÓN DE DEPENDENCIAS
    @Autowired
    @Qualifier("IPersonaRepository")
    private PersonaRepository IPersonaRepository;

    @Autowired
    private IUsuarioService usuarioService; // Solo necesita una referencia a IUsuarioService

    // LOGS DE ERROR
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(PersonaServiceImpl.class);

    @Override
    public boolean guardar(Persona persona) {
        try {
            if (persona == null) {
                logger.error("ERROR AGREGAR_PERSONA: LA PERSONA ES NULO!");
                return false;
            }

            if (usuarioService.existsByUsername(persona.getUsuario().getLogin())) {
                logger.error("ERROR AGREGAR_PERSONA: El username ya está registrado.");
                return false;
            }

            if (existsByEmail(persona.getEmail())) {
                logger.error("ERROR AGREGAR_PERSONA: El correo ya está registrado: " + persona.getEmail());
                return false;
            }

            if (existsByIdentificacion(persona.getIdentificacion())) {
                logger.error("ERROR AGREGAR_PERSONA: La identificación ya está registrada: " + persona.getIdentificacion());
                return false;
            }

            if (persona.getUsuario() != null) {
                persona.getUsuario().setPersona(persona);
            }

            IPersonaRepository.save(persona);
            return true;
        } catch (Exception e) {
            logger.error("ERROR AGREGAR_PERSONA: LA PERSONA NO SE HA GUARDADO!", e);
            return false;
        }
    }



	
	
	//UPDATE
    @Override
    public boolean actualizar(Persona persona) {
        try {
            if (persona == null || persona.getId() == 0) {
                logger.error("ERROR EDITAR_PERSONA: LA PERSONA ES NULO O EL ID ES 0!");
                return false;
            } else {
                if (persona.getUsuario() != null) {
                    persona.getUsuario().setPersona(persona);
                }
                IPersonaRepository.save(persona);
                return true;
            }
        } catch(Exception e) {
            logger.error("ERROR EDITAR_PERSONA: LA PERSONA NO SE HA EDITADO!", e);
            return false;
        }
    }
	//DELETE
    @Override
    public boolean eliminar(int id) {
        try {
            if (id == 0) {
                logger.error("ERROR ELIMINAR_PERSONA: EL ID DEL PERSONA ES 0!");
                return false;
            } else {
                Persona persona = IPersonaRepository.findById(id);
                IPersonaRepository.delete(persona);
                return true;
            }
        } catch(Exception e) {
            logger.error("ERROR ELIMINAR_PERSONA: LA PERSONA NO SE HA ELIMINADO!", e);
            return false;
        }
    }
	//LISTA DE PRODUCTOS
	@Override
	public List<Persona> consultarPersona(Pageable pageable) {
		return  IPersonaRepository.findAll(pageable).getContent(); 
	}

	//================ METODOS DE BUSQUEDA =============================
	//PERSONA POR ID | VALOR UNICO
	@Override
	public Persona findById(int id) {
		return IPersonaRepository.findById(id);
	}


	//LISTA DE PERSONAS POR EDAD
	@Override
	public List<Persona> findByEdad(int edad) {
		return IPersonaRepository.findByEdad(edad); 
	}
	@Override
    public Persona findByIdentificacion(int identificacion) {
    	return IPersonaRepository.findByIdentificacion(identificacion);
    }
	

    @Override
    public List<Persona> findBySNombre(String SNombre) {
        return IPersonaRepository.findBySNombre(SNombre); // Devuelve la lista de personas con el segundo nombre especificado.
    }
    
    
    @Override
    public List<Persona> findByPApellido(String PApellido) {
        return IPersonaRepository.findByPApellido(PApellido); // Devuelve la lista de personas con el primer apellido especificado.
    }

    @Override
    public List<Persona> findBySApellido(String SApellido) {
        return IPersonaRepository.findBySApellido(SApellido); // Devuelve la lista de personas con el segundo apellido especificado.
    }

    @Override
    public List<Persona> findByEmail(String email) {
        return IPersonaRepository.findByEmail(email); // Devuelve la lista de personas con el email especificado.
    }

    @Override
    public List<Persona> findByFechaNacimiento(LocalDate fechaNacimiento) {
        return IPersonaRepository.findByFechaNacimiento(fechaNacimiento); // Devuelve la lista de personas con la fecha de nacimiento especificada.
    }

    @Override
    public List<Persona> findByEdadClinica(String edadClinica) {
        return IPersonaRepository.findByEdadClinica(edadClinica); // Devuelve la lista de personas con la edad clínica especificada.
    }
    
    @Override
    public List<Persona> findByPnombre(String pnombre) {
        return IPersonaRepository.findByPnombre(pnombre); // Devuelve la lista de personas con el primer nombre especificado.
    }

    @Override
    public boolean existsByEmail(String email) {
        List<Persona> personas = IPersonaRepository.findByEmail(email);
        return personas != null && !personas.isEmpty(); // Verifica que la lista no sea nula y que no esté vacía
    }

	@Override
	public boolean existsByIdentificacion(int identificacion) {
		return IPersonaRepository.findByIdentificacion(identificacion) != null; // Asegúrate de que este método exista
	}

	

}
