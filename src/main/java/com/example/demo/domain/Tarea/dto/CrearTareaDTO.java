package com.example.demo.domain.Tarea.dto;

import java.util.Date;

public record CrearTareaDTO(
        Long curso,
        String titulo,
        String descripcion,
        Date fechaEntrega,
        Integer semana
) {
}
