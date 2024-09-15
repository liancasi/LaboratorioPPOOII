package com.PPOOII.Laboratorio.Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Entities.UsuarioPK;
import com.PPOOII.Laboratorio.Repository.UsuarioRepository;
import com.PPOOII.Laboratorio.Services.Interfaces.IUsuarioService;

@Service("UsuarioService")
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {
	// ========= INYECCIÓN DE DEPENDENCIAS ==========
	@Autowired
	@Qualifier("IUsuarioRepository")
	private UsuarioRepository IUsuarioRepository;
	//==================== LOGS ============================
	//LOGS DE ERROR
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(PersonaServiceImpl.class);
	//INSERT
	@Override
	public boolean guardar(Usuario usuario) {
		try {
			if (usuario == null) {
				logger.error("ERROR AGREGAR_USUARIO: EL USUARIO ES NULO!");
				return false;				
			}
			else {
				IUsuarioRepository.save(usuario);
				return true;
			}
		}catch(Exception e) {
			logger.error("ERROR AGREGAR_USUARIO: EL USUARIO NO SE HA GUARDADO!");
			return false;
		}
	}
	//UPDATE
	@Override
	public boolean actualizar(Usuario usuario) {
		try {
			if ((usuario == null) 
					|| (usuario.getId().getPersona() == 0) 
					|| (usuario.getId().getLogin().isEmpty()) 
					|| (usuario.getId().getLogin() == null)) 
			{
				logger.error("ERROR EDITAR_PERSONA:  EL USUARIO ES NULO O EL ID ES 0 O EL LOGIN ES NULL!");		
				return false;
			}
			else {	
				IUsuarioRepository.save(usuario);
				return true;
			}
		}catch(Exception e) {
			logger.error("ERROR EDITAR_PERSONA: EL USUARIO NO SE HA EDITADO!");		
			return false;
		}
	}
	//DELETE
	@Override
	public boolean eliminar(UsuarioPK id) {
		try {
			if ((id.getPersona() == 0) || (id.getLogin().isEmpty()) || id.getLogin() == null) {
				logger.error("ERROR ELIMINAR_PERSONA: EL ID DEL USUARIO ES 0 O NULL!");
				return false;
			}
			else {
				Usuario usuario = IUsuarioRepository.getUsuarioANDPersona(id.getLogin(), id.getPersona());
				IUsuarioRepository.delete(usuario);
				return true;
			}
		}catch(Exception e) {
			logger.error("ERROR ELIMINAR_PERSONA: LA PERSONA NO SE HA ELIMINADO!");
			return false;
		} 
	}
	//LISTA DE PRODUCTOS
	@Override
	public List<Usuario> consultarUsuario(Pageable pageable) {
		return  IUsuarioRepository.findAll(pageable).getContent();  
	}
	@Override
	public Usuario getUsuarioById(UsuarioPK id) {
		return IUsuarioRepository.getUsuarioANDPersona(id.getLogin(), id.getPersona());
	}
	
	public Usuario findByUsernameANDAPIKey(String login, String apikey) {
		return IUsuarioRepository.findByUsernameANDAPIKey(login, apikey);
	}
	
	//CONSULTA DE CREDENCIALES
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Buscar el usuario con el repositorio y si no existe lanzar una exepcion. ");
    	//Buscar el usuario  por el login y basados en la consulta JPQL
		Usuario appUser = IUsuarioRepository.findByUsername(username);
    	List grantList = new ArrayList(); // Este objeto es usado para manejar roles de usuario. en este caso NO APLICA.
    	System.out.println("Crear El objeto UserDetails que va a ir en sesion y retornarlo.");
    	//Crear El objeto UserDetails que va a ir en sesion y retornarlo.
    	UserDetails user = (UserDetails) new User(appUser.getId().getLogin(), appUser.getPassword(), grantList);
    	System.out.println("user:["+user+"]");
    	return user;
	}
}
