package org.utl.conferencias.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.utl.conferencias.entidad.Alumno;
import org.utl.conferencias.entidad.Carrera;
import org.utl.conferencias.entidad.Conferencia;
import org.utl.conferencias.repositorio.AlumnoRepositorio;
import org.utl.conferencias.repositorio.CarreraRepositorio;
import org.utl.conferencias.repositorio.ConferenciaRepositorio;
import org.utl.conferencias.repositorio.PersonaRepositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConferenciaServicio {
    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Autowired
    private ConferenciaRepositorio conferenciaRepositorio;
    @Autowired
    private AlumnoRepositorio alumnoRepositorio;
    @Autowired
    private CarreraRepositorio carreraRepositorio;

    private static final int TOLERANCIA_MINUTOS = 30;

    public Optional<Conferencia> obtenerConferencia(Long id) {
        return conferenciaRepositorio.findById(id);
    }

    public Optional<Conferencia> verificarConferencia(Long id) {
        return conferenciaRepositorio.findByIdAndFechaHoraBetween(id, LocalDateTime.now(), LocalDateTime.now().plusWeeks(1));
    }

    public Optional<Alumno> obtenerAlumno(String matricula) {
        return alumnoRepositorio.findById(matricula);
    }

    public List<Carrera> obtenerCarreras() {
        return (List<Carrera>) carreraRepositorio.findAll();
    }

    public List<Conferencia> obtenerConferencias() {
        return conferenciaRepositorio.findConferenciasByFechaHoraBetween(LocalDateTime.now().minusMinutes(TOLERANCIA_MINUTOS), LocalDateTime.now().plusWeeks(1));
    }

    public Alumno registrar(Alumno alumno, Long conferencia) {
        if (!alumnoRepositorio.findById(alumno.getMatricula()).isPresent())
            personaRepositorio.save(alumno.getPersona());
        else alumno = alumnoRepositorio.findById(alumno.getMatricula()).get();
        alumno.agregar(conferenciaRepositorio.findById(conferencia).get());
        return alumnoRepositorio.save(alumno);
    }

    public Alumno ingresar(String matricula) {
        Optional<Alumno> alumnoOptional = alumnoRepositorio.findById(matricula);
        if (!alumnoOptional.isPresent()) return null;
        System.out.println("El alumno existe.");
        LocalDateTime minimo = LocalDateTime.now().minusMinutes(TOLERANCIA_MINUTOS);
        LocalDateTime maximo = LocalDateTime.now().plusMinutes(TOLERANCIA_MINUTOS);
        Optional<Conferencia> conferenciaOptional = conferenciaRepositorio.findByFechaHoraBetween(minimo, maximo);
        if (!conferenciaOptional.isPresent()) return null;
        System.out.println("Habrá conferencia en menos de 30 minutos.");
        alumnoOptional = alumnoRepositorio.findByMatriculaAndRegistros(matricula, conferenciaOptional.get());
        if (!alumnoOptional.isPresent()) return null;
        System.out.println("El alumno está registrado.");
        Conferencia conferencia = conferenciaOptional.get();
        conferencia.agregar(alumnoOptional.get());
        conferenciaRepositorio.save(conferencia);
        return alumnoOptional.get();
    }

    public boolean verificar(String matricula, String email) {
        Alumno alumno = alumnoRepositorio.findByMatriculaAndPersonaEmail(matricula, email);
        if (alumno == null) return true;
        boolean isCreating = (matricula == null);
        if (isCreating && alumno != null) return false;
        if (alumno.getMatricula() != matricula) return false;
        return true;
    }
}
