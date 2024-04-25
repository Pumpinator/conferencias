package org.utl.conferencias.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.utl.conferencias.entidad.Alumno;
import org.utl.conferencias.entidad.Conferencia;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepositorio extends CrudRepository<Alumno, String> {
    Optional<Alumno> findByMatriculaAndRegistros(String matricula, Conferencia conferencia);

    Alumno findByMatriculaAndPersonaEmail(String matricula, String email);

    List<Alumno> findByRegistros(Conferencia conferencia);
}
