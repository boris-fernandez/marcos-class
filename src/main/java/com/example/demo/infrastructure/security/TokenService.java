package com.example.demo.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.demo.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret.key}")
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Marly Handmade")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getIdUsuario())
                    .withClaim("rol", usuario.getRol())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }

    public String getSubject(String token){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Marly Handmade")
                    .build();

            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception){
            throw new RuntimeException(exception);
        }

        return decodedJWT.getSubject();
    }


    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Marly Handmade")
                    .build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido o expirado", exception);
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-05:00"));
    }

    private Instant generarFechaExpiracionResetPassword(){
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-05:00"));
    }

}
