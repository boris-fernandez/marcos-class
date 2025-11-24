package com.example.demo.domain.publicacionForo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionForoRepository extends JpaRepository<PublicacionForo, Long> {
    List<PublicacionForo> findAllByTemaForo_IdTema(Long temaForoIdTema);
}
