package com.example.demo.domain.inscripcion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findAllByEstudiante_IdUsuario(Long estudianteIdUsuario);
}
