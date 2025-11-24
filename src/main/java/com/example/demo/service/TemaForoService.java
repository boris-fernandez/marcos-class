package com.example.demo.service;

import com.example.demo.domain.TemaForo.TemaForo;
import com.example.demo.domain.TemaForo.TemaForoRepository;
import com.example.demo.domain.TemaForo.dto.CrearTemaForoDTO;
import com.example.demo.domain.TemaForo.dto.TemaForoDTO;
import com.example.demo.domain.curso.Curso;
import com.example.demo.domain.curso.CursoRepository;
import com.example.demo.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TemaForoService {

    @Autowired
    private TemaForoRepository temaForoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Usuario getUsuarioActual() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    //CREAR TEMA FORO
    @PreAuthorize("hasAnyRole('PROFESOR', 'ADMIN')")
    public TemaForoDTO crearTemaForo(CrearTemaForoDTO crearTemaForoDTO){
        Curso curso = cursoRepository.findById(crearTemaForoDTO.curso()).orElseThrow(()-> new RuntimeException("No existe un curso con este id: " + crearTemaForoDTO.curso()));
        Usuario usuario = getUsuarioActual();
        if(!usuario.getRol().equals("PROFESOR")) throw new RuntimeException("Solo usuarios con rol profesor pueden crear foros");

        TemaForo temaForo = TemaForo.builder()
                .curso(curso)
                .titulo(crearTemaForoDTO.titulo())
                .fechaCreacion(new Date())
                .creador(usuario)
                .estado(true)
                .build();
        temaForoRepository.save(temaForo);
        return new TemaForoDTO(temaForo);
    }

    //EDITAR TEMA FORO
    @Transactional
    @PreAuthorize("hasAnyRole('PROFESOR', 'ADMIN')")
    public TemaForoDTO editarTemaForo(Long idForo ,CrearTemaForoDTO temaForoDTO){
        TemaForo temaForo = temaForoRepository.findById(idForo).orElseThrow(()-> new RuntimeException("No existe un foro con ese id: " + idForo));
        if (!temaForo.getEstado()) throw new RuntimeException("El foro está inactivo");
        if (temaForoDTO.titulo() != null) temaForo.setTitulo(temaForoDTO.titulo());
        return new TemaForoDTO(temaForo);
    }

    //LISTAR TEMAS FORO
    public List<TemaForoDTO> listarTemaForoPorCurso(Long idCurso){
        return temaForoRepository.findAllByCurso_IdCursoAndEstadoTrue(idCurso).stream().map(TemaForoDTO::new).toList();
    }

    //MOSTRAR TEMA FORO POR ID
    public TemaForoDTO mostrarTemaForoPorId(Long idForo){
        return new TemaForoDTO(temaForoRepository.findById(idForo).orElseThrow(() -> new RuntimeException("No existe un foro con este id: " + idForo)));
    }

    //ELIMINAR TEMA FORO
    @Transactional
    @PreAuthorize("hasAnyRole('PROFESOR', 'ADMIN')")
    public void eliminarTemaForo(Long idForo){
        TemaForo temaForo = temaForoRepository.findById(idForo).orElseThrow(()-> new RuntimeException("No existe un foro con ese id: " + idForo));
        temaForo.setEstado(false);
    }
}
