package com.PPOOII.Laboratorio.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PPOOII.Laboratorio.Config.PasswordChangeRequest;
import com.PPOOII.Laboratorio.Entities.Persona;
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Entities.UsuarioPK;
import com.PPOOII.Laboratorio.Services.UsuarioServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
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
    
    @PutMapping("/usuario/changePassword/{id}")
    public ResponseEntity<String> changePassword(@PathVariable int id, @RequestBody String newPassword) {
        boolean success = usuarioService.cambiarPassword(id, newPassword);
        if (success) {
            return ResponseEntity.ok("Contraseña cambiada exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
    }
    

    // METODO PUT
    @PutMapping("/usuario")
    public boolean editarUsuario(@RequestBody @Validated Usuario usuario) {
        return usuarioService.actualizar(usuario);
    }

    // METODO DELETE
    @DeleteMapping("/usuario/{login}/{persona}")
    public boolean eliminarUsuario(@PathVariable("login") String login, @PathVariable("persona") int persona) {
        UsuarioPK id = new UsuarioPK(login, persona);
        return usuarioService.eliminar(id);
    }

    // METODO GET
    @GetMapping("/usuarios")
    public List<Usuario> listadoUsuario(Pageable pageable) {
        return usuarioService.consultarUsuario(pageable);
    }

    // ============= METODOS HTTP DE BUSQUEDA =============
    @GetMapping("/usuario/{login}/{persona}")
    public ResponseEntity<?> getUsuarioByLoginAndPersona(
    		@PathVariable("login") String login, 
    		@PathVariable("persona") int persona) {
        UsuarioPK usuarioPK = new UsuarioPK(login, persona);
        Usuario usuario = usuarioService.getUsuarioById(usuarioPK);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    
}
