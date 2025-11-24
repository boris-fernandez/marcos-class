package com.example.demo.domain.usuario.dto;

public record RegistrarUsuario(
        String nombre,
        String email,
        String contrasena,
        String rol
) {
}
