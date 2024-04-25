package org.utl.conferencias.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;
import org.utl.conferencias.entidad.Alumno;
import org.utl.conferencias.entidad.Carrera;
import org.utl.conferencias.entidad.Conferencia;
import org.utl.conferencias.servicio.ConferenciaServicio;
import org.utl.conferencias.util.FotosUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
public class ConferenciaControlador {
    @Autowired
    private ConferenciaServicio conferenciaServicio;

    @GetMapping("/conferencias")
    public String verConferencias(Model model) {
        List<Conferencia> conferencias = conferenciaServicio.obtenerConferencias();
        model.addAttribute("conferencias", conferencias);
        return "conferencia/conferencias";
    }

    @GetMapping("/conferencia/{id}/registro")
    public String verRegistro(@PathVariable(name = "id") Long id, Model model) {
        Alumno alumo = new Alumno();
        List<Carrera> carreras = conferenciaServicio.obtenerCarreras();
        Optional<Conferencia> conferenciaOptional = conferenciaServicio.verificarConferencia(id);
        if (conferenciaOptional.isEmpty()) return "redirect:/conferencias";
        model.addAttribute("conferencia", conferenciaOptional.get());
        model.addAttribute("alumno", alumo);
        model.addAttribute("carreras", carreras);
        return "conferencia/registro";
    }

    @PostMapping("/conferencia/registrar")
    public String registrar(Alumno alumno, Long conferencia, RedirectAttributes redirectAttributes) throws IOException {
        conferenciaServicio.registrar(alumno, conferencia);
        return redirect(conferencia);
    }

    @GetMapping("/conferencia/{id}")
    public String verConferencia(@PathVariable(name = "id") Long id, Model model) throws ParseException {
        Optional<Conferencia> conferenciaOptional = conferenciaServicio.obtenerConferencia(id);
        if (conferenciaOptional.isPresent()) model.addAttribute("conferencia", conferenciaOptional.get());
        return "conferencia/conferencia";
    }

    public String redirect(Long id) {
        return "redirect:/conferencia/" + id;
    }

}
