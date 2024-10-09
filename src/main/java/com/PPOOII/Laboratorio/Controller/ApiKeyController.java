package com.PPOOII.Laboratorio.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Services.UsuarioServiceImpl;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/apiKey") // Añadir la ruta aquí
public class ApiKeyController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping // Cambiado para que coincida con la ruta del controlador
    public ResponseEntity<?> getApiKey(@RequestHeader("username") String username) {
        Usuario user = usuarioService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(Collections.singletonMap("apiKey", user.getApikey()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Collections.singletonMap("error", "Usuario no encontrado"));
        }
    }
}