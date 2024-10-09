package com.PPOOII.Laboratorio.Services.Interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Entities.UsuarioPK;

public interface IUsuarioService {

	// METODOS CRUD
	boolean guardar(Usuario usuario);

	boolean actualizar(Usuario usuario);

	boolean eliminar(UsuarioPK id);

	List<Usuario> consultarUsuario(Pageable pageable);

	Usuario getUsuarioById(UsuarioPK id);

	Usuario getUsuarioANDPersona(String login, int persona);

	Usuario findByUsername(String login);

	Usuario findByUsernameANDAPIKey(String login, String apikey);

	boolean cambiarPassword(int id, String newPassword);

	boolean existsByUsername(String username);



}
