package org.utl.conferencias.rest;

import org.springframework.data.repository.query.Param;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.utl.conferencias.entidad.Alumno;
import org.utl.conferencias.servicio.ConferenciaServicio;

@RestController
@RequestMapping("/api")
public class ConferenciaRest {
    private ConferenciaServicio conferenciaServicio;
    private SimpMessagingTemplate template;

    public ConferenciaRest(ConferenciaServicio conferenciaServicio, SimpMessagingTemplate template) {
        this.conferenciaServicio = conferenciaServicio;
        this.template = template;
    }

    @GetMapping("/ingreso")
    public boolean ingresar(@RequestParam String matricula) throws Exception {
        Alumno alumno = conferenciaServicio.ingresar(matricula);
        if (alumno != null) {
            alumno.setRegistros(null);
            template.convertAndSend("/conferencia/asistentes", alumno);
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/verificar")
    public String verificar(@Param("matricula") String matricula, @Param("email") String email) {
        return conferenciaServicio.verificar(matricula, email) ? "Ok" : "Error";
    }

}
