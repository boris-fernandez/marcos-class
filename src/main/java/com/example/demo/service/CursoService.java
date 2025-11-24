package com.example.demo.service;

import com.example.demo.domain.curso.Curso;
import com.example.demo.domain.curso.CursoRepository;
import com.example.demo.domain.curso.dto.CursoCrear;
import com.example.demo.domain.curso.dto.CursoDTO;
import com.example.demo.domain.usuario.Usuario;
import com.example.demo.domain.usuario.UsuarioRepositoy;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepositoy usuarioRepositoy;

    //crear curso
    @PreAuthorize("hasRole('ADMIN')")
    public CursoDTO crearCurso(CursoCrear crear){
        Usuario usuario = usuarioRepositoy.findByNombreAndRol(crear.profesor(), "PROFESOR").orElseThrow(()-> new RuntimeException("No existe un profesor con ese nombre"));

        Curso curso = Curso.builder()
                .nombre(crear.nombre())
                .descripcion(crear.descripcion())
                .profesor(usuario)
                .build();

        cursoRepository.save(curso);
        return new CursoDTO(curso);
    }

    //listar curso
    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll().stream().map(CursoDTO::new).toList();
    }

    //eliminar curso
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe un curso con ese id"));
        curso.cambiarEstado();
        cursoRepository.save(curso);
    }

    //actualizar curso
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public CursoDTO actualizarCurso(@Valid CursoCrear crear, Long id) {
        Usuario usuario = usuarioRepositoy.findByNombreAndRol(crear.profesor(), "PROFESOR").orElseThrow(()-> new RuntimeException("No existe un profesor con ese nombre"));

        Curso curso = cursoRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe un curso con ese id"));
        curso.actualizar(crear, usuario);
        return new CursoDTO(curso);
    }



}
