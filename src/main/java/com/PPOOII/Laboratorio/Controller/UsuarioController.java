package com.PPOOII.Laboratorio.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
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
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Entities.UsuarioPK;
import com.PPOOII.Laboratorio.Services.UsuarioServiceImpl;

@RestController
@RequestMapping("/Laboratorio1")
public class UsuarioController {
	
    // ============= INYECCION DEL SERVICE =============
    @Autowired
    @Qualifier("UsuarioService")
    UsuarioServiceImpl usuarioService;
    
    // ============= METODOS HTTP =============
    // METODO POST
    @PostMapping("/usuario")
    public boolean agregarUsuario(@RequestBody @Validated Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    // METODO PUT
    @PutMapping("/usuario")
    public boolean editarUsuario(@RequestBody @Validated Usuario usuario) {
        return usuarioService.actualizar(usuario);
    }

    // METODO DELETE
    @DeleteMapping("/usuario/{id}")
    public boolean eliminarUsuario(@PathVariable("id") UsuarioPK id) {
        return usuarioService.eliminar(id);
    }

    // METODO GET
    @GetMapping("/usuarios")
    public List<Usuario> listadoUsuario(Pageable pageable) {
        return usuarioService.consultarUsuario(pageable);
    }

    // ============= METODOS HTTP DE BUSQUEDA =============
    // GET
    @GetMapping("/usuario/id/{id}")
    public Usuario getByIdPersona(@PathVariable("id") UsuarioPK id) {
        return usuarioService.getUsuarioById(id);
    }





    

}
