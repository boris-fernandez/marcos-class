package com.example.demo.domain.usuario.dto;

import com.example.demo.domain.usuario.Usuario;

public record UsuarioDto(
        Long idUsuario,
        String nombre,
        String email,
        String contrasena,
        String rol
) {
    public UsuarioDto(Usuario usuario) {
        this(usuario.getIdUsuario(), usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getRol());
    }
}
