package com.example.demo.domain.CalificacionTarea.dto;

import com.example.demo.domain.CalificacionTarea.CalificacionTarea;

import java.util.Date;

public record CalificacionTareaDTO(
        Long IdCalificacion,
        Long entrega,
        Float califiacion,
        String comentariosProfesor,
        Date fechaCalificacion,
        String profesor
) {
    public CalificacionTareaDTO(CalificacionTarea calificacionTarea) {
        this(calificacionTarea.getIdCalificacion(), calificacionTarea.getEntrega().getIdEntrega(),calificacionTarea.getCalifiacion(),
                calificacionTarea.getComentariosProfesor(), calificacionTarea.getFechaCalificacion(), calificacionTarea.getProfesor().getNombre());
    }
}
