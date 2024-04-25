package org.utl.conferencias.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.utl.conferencias.entidad.Persona;

@Repository
public interface PersonaRepositorio extends CrudRepository<Persona, Long> {
}
