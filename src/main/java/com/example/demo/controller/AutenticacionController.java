package com.example.demo.controller;

import com.example.demo.domain.usuario.Usuario;
import com.example.demo.domain.usuario.dto.AutenticacionDto;
import com.example.demo.domain.usuario.dto.RegistrarUsuario;
import com.example.demo.domain.usuario.dto.UsuarioDto;
import com.example.demo.infrastructure.security.DatosJWTToken;
import com.example.demo.infrastructure.security.TokenService;
import com.example.demo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("auth")
public class AutenticacionController {

    @Autowired
    private AuthenticationConfiguration authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("login")
    public ResponseEntity<DatosJWTToken> autenticar(@RequestBody @Valid AutenticacionDto autenticacionDto){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                autenticacionDto.email(),
                autenticacionDto.contrasena()
        );
        var manager = authenticationManager.getAuthenticationManager();
        var usuarioAutenticado = manager.authenticate(authToken);
        String JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }

    @PostMapping("register")
    public ResponseEntity<UsuarioDto> registrar(@RequestBody @Valid RegistrarUsuario registrarUsuario, UriComponentsBuilder builder){
        UsuarioDto usuarioCreado =  usuarioService.registrar(registrarUsuario);
        URI uri = builder.path("/usuario/{id}").buildAndExpand(usuarioCreado.idUsuario()).toUri();
        return ResponseEntity.created(uri).body(usuarioCreado);
    }
}
