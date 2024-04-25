package org.utl.conferencias.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Conferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64)
    private String rutaFoto;
    @Column(length = 45, nullable = false)
    private String nombre;
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
    @ManyToOne
    @JoinColumn
    private Conferencista conferencista;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "conferencia_id"), inverseJoinColumns = @JoinColumn(name = "alumno_matricula"))
    private Set<Alumno> asistentes = new HashSet<>();

    public Conferencia(String rutaFoto, String nombre, LocalDateTime fechaHora, Conferencista conferencista) {
        this.rutaFoto = rutaFoto;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.conferencista = conferencista;
    }

    public void agregar(Alumno alumno) {
        this.asistentes.add(alumno);
    }

    @Transient
    public String getRutaFoto() {
        if (id == null || rutaFoto == null || rutaFoto.isEmpty()) return "/fotos/foto.png";
        return "/fotos/" + this.id + "/" + this.rutaFoto;
    }
}
