package com.example.demo.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositoy extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);

    Usuario findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Usuario> findByNombreAndRol(String nombre, String rol);

}
