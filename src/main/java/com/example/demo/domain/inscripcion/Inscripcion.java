package com.example.demo.domain.inscripcion;

import com.example.demo.domain.curso.Curso;
import com.example.demo.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Inscripcion", schema = "cursos")
@Entity
@Builder
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", referencedColumnName = "idCurso", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante", referencedColumnName = "idUsuario", nullable = false)
    private Usuario estudiante;

    private Date fechaInscripcion;
}
