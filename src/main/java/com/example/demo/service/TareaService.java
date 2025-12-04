package com.example.demo.service;

import com.example.demo.domain.Tarea.Tarea;
import com.example.demo.domain.Tarea.TareaRepository;
import com.example.demo.domain.Tarea.dto.CrearTareaDTO;
import com.example.demo.domain.Tarea.dto.TareDTO;
import com.example.demo.domain.curso.Curso;
import com.example.demo.domain.curso.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //CREAR
    @PreAuthorize("hasAnyRole('PROFESOR', 'ADMIN')")
    public TareDTO crearTarea(CrearTareaDTO crearTareaDTO){
        Tarea tarea = Tarea.builder()
                .curso(cursoRepository.findById(crearTareaDTO.curso()).orElseThrow(() -> new RuntimeException("No existe un curso con ese nombre: " + crearTareaDTO.curso())))
                .titulo(crearTareaDTO.titulo())
                .descripcion(crearTareaDTO.descripcion())
                .fechaEntrega(crearTareaDTO.fechaEntrega())
                .puntajeMaximo(20)
                .semana(crearTareaDTO.semana())
                .habilitado(true)
                .build();
        tareaRepository.save(tarea);
        return new TareDTO(tarea);
    }

    //ELIMINAR
    @Transactional
    @PreAuthorize("hasAnyRole('PROFESOR', 'ADMIN')")
    public void eliminar(Long id){
        Tarea tarea = tareaRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe una tarea con el id: " + id));
        tarea.cambiarEstado();
    }

    //EDITAR
    @Transactional
    @PreAuthorize("hasAnyRole('PROFESOR', 'ADMIN')")
    public TareDTO editarTarea(Long id, CrearTareaDTO editarTareaDTO){
        Curso curso = cursoRepository.findById(editarTareaDTO.curso()).orElseThrow(() -> new RuntimeException("No existe un curso con ese nombre: " + editarTareaDTO.curso()));
        Tarea tarea = tareaRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe una tarea con el id: " + id));
        tarea.editarTarea(editarTareaDTO, curso);
        return new TareDTO(tarea);
    }

    //LISTAR TAREA POR CURSO
    public List<TareDTO> listarTareas(Long idCurso){
        return tareaRepository.findAllByCurso_IdCurso(idCurso).stream().map(TareDTO::new).toList();
    }

    //MOSTRAR TARA POR ID
    public TareDTO tareaPorId(Long idTarea){
        return new TareDTO(tareaRepository.findById(idTarea).orElseThrow(()-> new RuntimeException("No existe una tarea con el id: " + idTarea)));
    }

    //ACTUALIZAR TAREA A DESABILITADO CUANDO PASE EL fechaEntrega
    @Scheduled(cron = "0 */1 * * * *", zone = "America/Lima")
    @Transactional
    public void desabilitarTareasExpiradas() {
        LocalDateTime ahora = LocalDateTime.now(ZoneId.of("America/Lima"));
        tareaRepository.desabilitarTareasExpiradas(ahora);
    }
}
