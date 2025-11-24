package com.example.demo.domain.Tarea.dto;

import com.example.demo.domain.Tarea.Tarea;

import java.util.Date;

public record TareDTO(
        Long idTarea,
        String curso,
        String titulo,
        String descripcion,
        Date fechaEntrega,
        Integer puntajeMaximo
) {
    public TareDTO(Tarea tarea) {
        this(tarea.getIdTarea(), tarea.getCurso().getNombre(), tarea.getTitulo(), tarea.getDescripcion(), tarea.getFechaEntrega(), tarea.getPuntajeMaximo());
    }
}
