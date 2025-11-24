package com.example.demo.domain.curso.dto;

import com.example.demo.domain.curso.Curso;

public record CursoDTO(
        Long idCurso,
        String nombre,
        String descripcion,
        String profesor
) {
    public CursoDTO(Curso curso) {
        this(curso.getIdCurso(), curso.getNombre(), curso.getDescripcion(), curso.getProfesor().getNombre());
    }
}
