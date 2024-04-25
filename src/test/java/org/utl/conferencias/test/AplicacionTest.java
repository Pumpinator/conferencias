package org.utl.conferencias.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.utl.conferencias.entidad.*;
import org.utl.conferencias.repositorio.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AplicacionTest {
    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Autowired
    private ConferencistaRepositorio conferencistaRepositorio;
    @Autowired
    private ConferenciaRepositorio conferenciaRepositorio;
    @Autowired
    private AlumnoRepositorio alumnoRepositorio;
    @Autowired
    private CarreraRepositorio carreraRepositorio;

    @Test
    public void testCrearConferencias() {
        Conferencia conferencia1 = new Conferencia(
                "billgates.jpg",
                "Nuevos Inventos Tecnológicos",
                LocalDateTime.now().plusMinutes(30),
                new Conferencista(new Persona(
                        "Bill",
                        "Gates",
                        "billgates@hotmail.com",
                        "1234567890"))
        );

        Conferencia conferencia2 = new Conferencia(
                "jeffbezos.jpg",
                "Cómputo en la Nube",
                LocalDateTime.now().plusHours(1).plusMinutes(30),
                new Conferencista(new Persona(
                        "Jeff",
                        "Bezos",
                        "jeffbezos@amazon.com",
                        "7894563210"))
        );

        Conferencia conferencia3 = new Conferencia(
                "elonmusk.jpg",
                "Viajes Espaciales",
                LocalDateTime.now().plusHours(2).plusMinutes(30),
                new Conferencista(new Persona(
                        "Elon",
                        "Musk",
                        "elonmusk@spacex.com",
                        "4561230987"))
        );

        personaRepositorio.saveAll(List.of(
                conferencia1.getConferencista().getPersona(),
                conferencia2.getConferencista().getPersona(),
                conferencia3.getConferencista().getPersona()
        ));

        conferencistaRepositorio.saveAll(List.of(
                conferencia1.getConferencista(),
                conferencia2.getConferencista(),
                conferencia3.getConferencista()
        ));

        conferenciaRepositorio.saveAll(List.of(
                conferencia1,
                conferencia2,
                conferencia3
        ));
    }

//    @Test
//    public void testListarAsistentes() {
//        Conferencia conferencia = conferenciaRepositorio.findById(1L).get();
//        List<Alumno> alumnos = alumnoRepositorio.findByRegistros(conferencia);
//        for(Alumno alumno : alumnos) {
//            alumno.setRegistros(null);
//            System.out.println(alumno);
//        }
//    }
//
//    @Test
//    public void testVerificarRegistro() {
//        Alumno alumno = alumnoRepositorio.findById("22002359").get();
//        Conferencia conferencia = conferenciaRepositorio.findByAsistentes(alumno).get();
//        System.out.println(conferencia);
//    }

    @Test
    public void testCrearCarreras() {
        Carrera dsm = new Carrera("Desarrollo de Software Multiplataforma");
        Carrera evn = new Carrera("Entornos Virtuales y Negocios Digitales");
        Carrera ird = new Carrera("Infraestructura de Redes Digitales");
        Carrera ga = new Carrera("Gastronomía");
        Carrera tu = new Carrera("Turismo");
        carreraRepositorio.saveAll(List.of(dsm, evn, ird, ga, tu));
    }
}
