package com.example.demo.domain.CalificacionTarea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalificacionTareaRepository extends JpaRepository<CalificacionTarea, Long> {
    Optional<CalificacionTarea> findByEntrega_IdEntregaAndEntrega_Estudiante_IdUsuario(Long idEntrega, Long idUsuario);

    boolean existsByEntrega_IdEntrega(Long idEntrega);
}
