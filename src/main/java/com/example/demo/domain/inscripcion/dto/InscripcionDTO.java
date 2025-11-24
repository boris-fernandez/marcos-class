package com.example.demo.domain.inscripcion.dto;

import com.example.demo.domain.curso.Curso;
import com.example.demo.domain.curso.dto.CursoDTO;
import com.example.demo.domain.inscripcion.Inscripcion;

import java.util.Date;

public record InscripcionDTO(
        Long idInscripcion,
        CursoDTO curso,
        String estudiante,
        Date fechaInscripcion
) {
    public InscripcionDTO(Inscripcion inscripcion) {
        this(inscripcion.getIdInscripcion(), new CursoDTO(inscripcion.getCurso()), inscripcion.getEstudiante().getNombre(),
                inscripcion.getFechaInscripcion());
    }
}
