package com.example.demo.service;

import com.example.demo.domain.curso.Curso;
import com.example.demo.domain.curso.CursoRepository;
import com.example.demo.domain.inscripcion.Inscripcion;
import com.example.demo.domain.inscripcion.InscripcionRepository;
import com.example.demo.domain.inscripcion.dto.InscripcionCrear;
import com.example.demo.domain.inscripcion.dto.InscripcionDTO;
import com.example.demo.domain.usuario.Usuario;
import com.example.demo.domain.usuario.UsuarioRepositoy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepositoy usuarioRepositoy;

    //crear
    @PreAuthorize("hasRole('ADMIN')")
    public InscripcionDTO crearInscripcion(InscripcionCrear crear){
        Curso curso = cursoRepository.findByNombre(crear.curso()).orElseThrow(()-> new RuntimeException("No existe un curso con ese nombre"));
        Usuario usuario = usuarioRepositoy.findByNombreAndRol(crear.estudiante(), "ESTUDIANTE").orElseThrow(()-> new RuntimeException("No existe un estudiante con ese nombre"));

        Inscripcion inscripcion = Inscripcion.builder()
                .curso(curso)
                .estudiante(usuario)
                .fechaInscripcion(new Date())
                .build();
        inscripcionRepository.save(inscripcion);
        return new InscripcionDTO(inscripcion);
    }

    //listar
    public List<InscripcionDTO> listarInscripciones(){
        return inscripcionRepository.findAll().stream().map(InscripcionDTO::new).toList();
    }

    public List<InscripcionDTO> listarIncripcionesPorUsuario(Long idUsuario){
        return inscripcionRepository.findAllByEstudiante_IdUsuario(idUsuario).stream().map(InscripcionDTO::new).toList();
    }

    //eliminar
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarInscripcion(Long id){
        Inscripcion inscripcion = inscripcionRepository.findById(id).orElseThrow(()->new RuntimeException("No existe una inscripcion con ese id"));
        inscripcionRepository.deleteById(id);
    }
}
