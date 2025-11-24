package com.example.demo.domain.Tarea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findAllByCurso_IdCurso(Long cursoIdCurso);

    @Modifying
    @Query("UPDATE Tarea t SET t.habilitado = false WHERE t.habilitado = true AND t.fechaEntrega <= :now")
    int desabilitarTareasExpiradas(@Param("now") LocalDateTime now);
}
