package com.example.demo.domain.TemaForo.dto;

import com.example.demo.domain.TemaForo.TemaForo;

import java.util.Date;

public record TemaForoDTO(
        Long idTema,
        String curso,
        String titulo,
        Date fechaCreacion,
        String creador
) {
    public TemaForoDTO(TemaForo temaForo) {
        this(temaForo.getIdTema(), temaForo.getCurso().getNombre(), temaForo.getTitulo(),
                temaForo.getFechaCreacion(), temaForo.getCreador().getNombre());
    }
}
