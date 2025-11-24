package com.example.demo.domain.entrega;

import com.example.demo.domain.Tarea.Tarea;
import com.example.demo.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Entrega", schema = "evaluacion")
@Entity
@Builder
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;

    @ManyToOne
    @JoinColumn(name = "id_tarea", nullable = false)
    private Tarea tarea;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Usuario estudiante;

    private Date fechaEntrega;

    private String urlArchivo;

    private String estado;
}
