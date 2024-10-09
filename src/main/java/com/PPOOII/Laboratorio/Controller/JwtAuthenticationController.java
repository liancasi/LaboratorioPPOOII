package com.PPOOII.Laboratorio.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.PPOOII.Laboratorio.Config.JWTAuthenticationConfig;
import com.PPOOII.Laboratorio.Config.Model.JwtRequest;
import com.PPOOII.Laboratorio.Config.Model.JwtResponse;
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Services.UsuarioServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class JwtAuthenticationController {
    @Autowired
    JWTAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    @Qualifier("UsuarioService")
    private UsuarioServiceImpl usuarioServiceImp;

    @RequestMapping(
            value = "/authenticate",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, @RequestHeader("apikey") String apikey)
            throws Exception {
        System.out.println("********************************************************************");
        System.out.println("authenticationRequest.getUsername(): [" + authenticationRequest.getUsername() + "]");
        System.out.println("authenticationRequest.getPassword(): [" + authenticationRequest.getPassword() + "]");
        System.out.println("APIKey: [" + apikey + "]");
        System.out.println("********************************************************************");

        // Buscar el usuario utilizando el login y la API Key
        Usuario user = usuarioServiceImp.findByUsernameANDAPIKey(authenticationRequest.getUsername(), apikey);

        // Verificar si el usuario existe
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        // Verificar la contraseña (suponiendo que la contraseña se almacena de forma segura)
        if (!user.getPassword().equals(authenticationRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        // Cargar los detalles del usuario
        final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(user.getId().getLogin());

        // Generar el token JWT
        final String token = jwtAuthenticationConfig.getJWTToken(user.getId().getLogin());
        System.out.println("********************************************************************");
        System.out.println("token: [" + token + "]");
        System.out.println("********************************************************************");

        return ResponseEntity.ok(new JwtResponse(token, apikey));
    }
}