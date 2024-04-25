package org.utl.conferencias.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.utl.conferencias.entidad.Conferencista;

@Repository
public interface ConferencistaRepositorio extends CrudRepository<Conferencista, Long> {
}
