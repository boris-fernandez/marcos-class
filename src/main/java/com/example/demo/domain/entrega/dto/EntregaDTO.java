package com.example.demo.domain.entrega.dto;

import com.example.demo.domain.entrega.Entrega;

import java.util.Date;

public record EntregaDTO(
        Long idEntrega,
        Long tarea,
        String estudiante,
        Date fechaEntrega,
        String urlArchivo,
        String estado
) {
    public EntregaDTO(Entrega entrega) {
        this(entrega.getIdEntrega(), entrega.getTarea().getIdTarea(), entrega.getEstudiante().getNombre(),
                entrega.getFechaEntrega(), entrega.getUrlArchivo(), entrega.getEstado());
    }
}
