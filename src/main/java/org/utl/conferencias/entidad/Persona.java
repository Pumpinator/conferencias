package org.utl.conferencias.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false)
    private String nombres;
    @Column(length = 45, nullable = false)
    private String apellidos;
    @Column(length = 128, nullable = false)
    private String email;
    @Column(length = 10, nullable = false)
    private String telefono;

    public Persona(String nombres, String apellidos, String email, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
    }

    @Transient
    public String getNombreCompleto() {
        return this.nombres + " " + this.apellidos;
    }
}
