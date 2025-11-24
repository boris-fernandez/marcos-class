package com.example.demo.service;

import com.example.demo.domain.Tarea.TareaRepository;
import com.example.demo.domain.entrega.Entrega;
import com.example.demo.domain.entrega.EntregaRepository;
import com.example.demo.domain.entrega.dto.CrearEntregaDTO;
import com.example.demo.domain.entrega.dto.EntregaDTO;
import com.example.demo.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private TareaRepository tareaRepository;

    public Usuario getUsuarioActual() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //Crear entrega
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public EntregaDTO CrearEntrega(CrearEntregaDTO crearEntregaDTO){
        Usuario usuario = getUsuarioActual();
        if (!usuario.getRol().equals("ESTUDIANTE")) throw new RuntimeException("Solo usuarios con rol de estudiante pueden entregar tareas");

        Entrega entrega = Entrega.builder()
                .tarea(tareaRepository.findById(crearEntregaDTO.tarea())
                        .orElseThrow(()-> new RuntimeException("No existe una tarea con ese id: " + crearEntregaDTO.tarea())))
                .estudiante(usuario)
                .fechaEntrega(new Date())
                .urlArchivo(crearEntregaDTO.urlArchivo())
                .estado("Pendiente")
                .build();
        entregaRepository.save(entrega);

        return new EntregaDTO(entrega);
    }

    //listar Entrega
    public List<EntregaDTO> listarEntrega(){
        return entregaRepository.findAll().stream().map(EntregaDTO::new).toList();
    }

    //ENTREGA POR ID
    public EntregaDTO entregaPorId(Long id){
        return new EntregaDTO(entregaRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe una entrega con ese id: " + id)));
    }
}
