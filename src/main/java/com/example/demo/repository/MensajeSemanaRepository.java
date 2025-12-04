package com.example.demo.repository;

import com.example.demo.domain.mensaje.MensajeSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeSemanaRepository extends JpaRepository<MensajeSemana, Long> {
    List<MensajeSemana> findByIdCurso(Integer idCurso);
    List<MensajeSemana> findByIdEstudiante(Integer idEstudiante);
    List<MensajeSemana> findBySemana(Integer semana);
}
