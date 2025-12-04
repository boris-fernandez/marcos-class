package com.example.demo.domain.TemaForo.dto;

public record CrearTemaForoDTO(
        Long curso,
        String titulo,
        String mensajeInicial,
        Integer semana
) {
}
