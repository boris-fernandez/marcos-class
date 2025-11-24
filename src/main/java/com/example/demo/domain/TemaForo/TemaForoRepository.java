package com.example.demo.domain.TemaForo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemaForoRepository extends JpaRepository<TemaForo, Long> {
    List<TemaForo> findAllByCurso_IdCursoAndEstadoTrue(Long idCurso);

}
