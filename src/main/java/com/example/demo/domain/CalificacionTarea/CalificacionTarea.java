package com.example.demo.domain.CalificacionTarea;

import com.example.demo.domain.CalificacionTarea.dto.CrearCalificacionTareaDTO;
import com.example.demo.domain.entrega.Entrega;
import com.example.demo.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CalificacionTarea", schema = "evaluacion")
@Entity
@Builder
public class CalificacionTarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdCalificacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrega", nullable = false)
    private Entrega entrega;

    private Float califiacion;

    private String comentariosProfesor;

    private Date fechaCalificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfesor", nullable = false)
    private Usuario profesor;

    public void actualizarCalificacionTarea(CrearCalificacionTareaDTO actualizarCalificacionTarea){
        if (actualizarCalificacionTarea.califiacion() != null) setCalifiacion(actualizarCalificacionTarea.califiacion());
        if (actualizarCalificacionTarea.comentariosProfesor() != null) setComentariosProfesor(actualizarCalificacionTarea.comentariosProfesor());
    }
}
