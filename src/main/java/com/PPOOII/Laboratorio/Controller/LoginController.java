package com.PPOOII.Laboratorio.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.PPOOII.Laboratorio.Services.Interfaces.IUsuarioService;
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Entities.UsuarioPK;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public String mostrarFormularioDeLogin() {
        return "Login";  // Nombre del archivo HTML para el formulario de login
    }

}
