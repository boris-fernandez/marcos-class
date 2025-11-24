package com.example.demo.domain.material.dto;

import com.example.demo.domain.material.Material;

import java.util.Date;

public record MaterialDTO(
        Long idMaterial,
        String curso,
        String titulo,
        String tipo,
        String urlArchivo,
        Date fechaSubida
) {
    public MaterialDTO(Material material) {
        this(material.getIdMaterial(), material.getCurso().getNombre(), material.getTitulo(), material.getTipo(), material.getUrlArchivo(), material.getFechaSubida());
    }
}
