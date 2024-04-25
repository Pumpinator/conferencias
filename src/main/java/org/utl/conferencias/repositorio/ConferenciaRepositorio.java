package org.utl.conferencias.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.utl.conferencias.entidad.Alumno;
import org.utl.conferencias.entidad.Conferencia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConferenciaRepositorio extends CrudRepository<Conferencia, Long> {

    Optional<Conferencia> findByIdAndFechaHoraBetween(Long id, LocalDateTime minimo, LocalDateTime maximo);

    Optional<Conferencia> findByFechaHoraBetween(LocalDateTime minimo, LocalDateTime maximo);
    List<Conferencia> findConferenciasByFechaHoraBetween(LocalDateTime minimo, LocalDateTime maximo);

    Optional<Conferencia> findByAsistentes(Alumno alumno);
}
