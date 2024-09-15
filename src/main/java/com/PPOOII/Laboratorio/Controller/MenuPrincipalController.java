package com.PPOOII.Laboratorio.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menuPrincipal")
public class MenuPrincipalController {

    @GetMapping
    public String mostrarMenu() {
        return "MenuPrincipal";
    }
}
