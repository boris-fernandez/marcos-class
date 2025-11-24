package com.example.demo.domain.publicacionForo.dto;

import com.example.demo.domain.publicacionForo.PublicacionForo;

import java.util.Date;

public record PublicacionForoDTO(
        Long idPublicacion,
        Long temaForo,
        String usuario,
        String contenido,
        Date fechaPublicacion
) {
    public PublicacionForoDTO(PublicacionForo publicacionForo) {
        this(publicacionForo.getIdPublicacion(), publicacionForo.getTemaForo().getIdTema(), publicacionForo.getUsuario().getNombre(),
                publicacionForo.getContenido(), publicacionForo.getFechaPublicacion());
    }
}
