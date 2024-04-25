package org.utl.conferencias.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppControlador {
    @GetMapping("")
    public String verInicio(Model model) {
        return "redirect:/conferencias";
    }
}
