package com.example.demo.service;

import com.example.demo.domain.usuario.Usuario;
import com.example.demo.domain.usuario.UsuarioRepositoy;
import com.example.demo.domain.usuario.dto.RegistrarUsuario;
import com.example.demo.domain.usuario.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositoy usuarioRepositoy;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //crear usuario
    @PreAuthorize("hasRole('ADMIN')")
    public UsuarioDto registrar(RegistrarUsuario registrarUsuario) {
        if(usuarioRepositoy.existsByEmail(registrarUsuario.email()))
            throw new RuntimeException("Ya existe una cuenta con ese email");

        Usuario usuario = Usuario.builder()
                .nombre(registrarUsuario.nombre())
                .email(registrarUsuario.email())
                .contrasena(passwordEncoder.encode(registrarUsuario.contrasena()))
                .rol(registrarUsuario.rol())
                .build();
        usuarioRepositoy.save(usuario);
        return new UsuarioDto(usuario);
    }

}
