package com.example.demo.domain.TemaForo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemaForoRepository extends JpaRepository<TemaForo, Long> {
    List<TemaForo> findAllByCurso_IdCursoAndEstadoTrue(Long idCurso);

    Optional<TemaForo> findBySemana(Integer semana);
}
