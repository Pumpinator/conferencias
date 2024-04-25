package org.utl.conferencias.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Alumno {
    @Id
    @Column(length = 8)
    private String matricula;
    @ManyToOne
    @JoinColumn
    private Carrera carrera;
    @OneToOne
    @JoinColumn
    private Persona persona;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "alumno_matricula"), inverseJoinColumns = @JoinColumn(name = "conferencia_id"))
    private Set<Conferencia> registros = new HashSet<>();

    public Alumno(String matricula, Carrera carrera, Persona persona) {
        this.matricula = matricula;
        this.carrera = carrera;
        this.persona = persona;
    }

    public void agregar(Conferencia conferencia) {
        this.registros.add(conferencia);
    }

}
