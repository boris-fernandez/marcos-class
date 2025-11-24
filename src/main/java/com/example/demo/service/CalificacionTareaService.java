package com.example.demo.service;

import com.example.demo.domain.CalificacionTarea.CalificacionTarea;
import com.example.demo.domain.CalificacionTarea.CalificacionTareaRepository;
import com.example.demo.domain.CalificacionTarea.dto.CalificacionTareaDTO;
import com.example.demo.domain.CalificacionTarea.dto.CrearCalificacionTareaDTO;
import com.example.demo.domain.entrega.Entrega;
import com.example.demo.domain.entrega.EntregaRepository;
import com.example.demo.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CalificacionTareaService {

    @Autowired
    private CalificacionTareaRepository calificacionTareaRepository;

    @Autowired
    private EntregaRepository entregaRepository;

    private Usuario getUsuarioActual() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //CREAR CALIFICACION
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public CalificacionTareaDTO crearCalificacionTarea(CrearCalificacionTareaDTO crearCalificacionTareaDTO){
        Usuario usuario = getUsuarioActual();
        if (!usuario.getRol().equals("PROFESOR")) throw new RuntimeException("Solo los profesores pueden calificar las entregas");

        Entrega entrega = entregaRepository.findById(crearCalificacionTareaDTO.entrega()).orElseThrow(()-> new RuntimeException("No existe una entrega con ese id: " + crearCalificacionTareaDTO.entrega()));
        if (calificacionTareaRepository.existsByEntrega_IdEntrega(entrega.getIdEntrega())) {
            throw new RuntimeException("Esta entrega ya fue calificada.");
        }
        entrega.setEstado("Calificado");
        entregaRepository.save(entrega);

        CalificacionTarea calificacionTarea = CalificacionTarea.builder()
                .entrega(entrega)
                .califiacion(crearCalificacionTareaDTO.califiacion())
                .comentariosProfesor(crearCalificacionTareaDTO.comentariosProfesor())
                .fechaCalificacion(new Date())
                .profesor(usuario)
                .build();

        calificacionTareaRepository.save(calificacionTarea);
        return new CalificacionTareaDTO(calificacionTarea);
    }

    //ACTUALIZAR CALIFICACION
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public CalificacionTareaDTO actualizarCalificacionTarea(Long idCalificacion ,CrearCalificacionTareaDTO actualizarCalificacionTarea){
        Usuario usuario = getUsuarioActual();
        if (!usuario.getRol().equals("PROFESOR")) throw new RuntimeException("Solo profesores pueden actualizar calificaciones");
        CalificacionTarea calificacionTarea = calificacionTareaRepository.findById(idCalificacion).orElseThrow(()-> new RuntimeException("No existe una calificacion con ese id: " + idCalificacion));
        calificacionTarea.actualizarCalificacionTarea(actualizarCalificacionTarea);
        return new CalificacionTareaDTO(calificacionTarea);
    }

    //MOSTRAR CALIFICACION POR USUARIO
    public CalificacionTareaDTO calificacionPorUsuario(Long idEntrega){
        Usuario usuario = getUsuarioActual();
        CalificacionTarea calificacion = calificacionTareaRepository
                .findByEntrega_IdEntregaAndEntrega_Estudiante_IdUsuario(idEntrega, usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Esta entrega no tiene calificación o no pertenece al usuario actual."));
        return new CalificacionTareaDTO(calificacion);
    }

}
