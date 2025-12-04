package com.example.demo.domain.TemaForo;

import com.example.demo.domain.curso.Curso;
import com.example.demo.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "temaforo", schema = "foro")
@Entity
@Builder
public class TemaForo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    private String titulo;

    private String mensajeInicial;

    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creador", nullable = false)
    private Usuario creador;

    private Boolean estado;

    private Integer semana;

}
