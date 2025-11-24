package com.example.demo.domain.CalificacionTarea.dto;

public record CrearCalificacionTareaDTO(
        Long entrega,
        Float califiacion,
        String comentariosProfesor
) {
}
