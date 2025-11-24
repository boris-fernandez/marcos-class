package com.example.demo.domain.Tarea;

import com.example.demo.domain.Tarea.dto.CrearTareaDTO;
import com.example.demo.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tarea", schema = "evaluacion")
@Entity
@Builder
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    private String titulo;

    private String descripcion;

    private Date fechaEntrega;

    private Integer puntajeMaximo;

    private Boolean habilitado;

    public void cambiarEstado(){
        setHabilitado(!habilitado);
    }

    public void editarTarea(CrearTareaDTO crearTareaDTO, Curso curso){
        if(curso != null) setCurso(curso);
        if (crearTareaDTO.titulo() != null) setTitulo(crearTareaDTO.titulo());
        if (crearTareaDTO.descripcion() != null) setDescripcion(crearTareaDTO.descripcion());
        if (crearTareaDTO.fechaEntrega() != null) setFechaEntrega(crearTareaDTO.fechaEntrega());
        if (crearTareaDTO.puntajeMaximo() != null) setPuntajeMaximo(crearTareaDTO.puntajeMaximo());
    }
}
