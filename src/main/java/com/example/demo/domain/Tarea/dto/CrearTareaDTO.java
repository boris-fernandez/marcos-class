package com.example.demo.domain.Tarea.dto;

import java.util.Date;

public record CrearTareaDTO(
        String curso,
        String titulo,
        String descripcion,
        Date fechaEntrega,
        Integer puntajeMaximo
) {
}
