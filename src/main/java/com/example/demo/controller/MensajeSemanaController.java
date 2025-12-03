package com.example.demo.controller;

import com.example.demo.domain.mensaje.MensajeSemana;
import com.example.demo.dto.MensajeSemanaCrear;
import com.example.demo.repository.MensajeSemanaRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/mensajes")
@CrossOrigin(origins = "*")
public class MensajeSemanaController {

    private final MensajeSemanaRepository repository;

    public MensajeSemanaController(MensajeSemanaRepository repository) {
        this.repository = repository;
    }

    // Listar todos los mensajes
    @GetMapping
    public List<MensajeSemana> getAll() {
        return repository.findAll();
    }

    // Listar mensajes de un curso específico
    @GetMapping("/curso/{idCurso}")
    public List<MensajeSemana> getByCurso(@PathVariable Integer idCurso) {
        return repository.findByIdCurso(idCurso);
    }

    // Listar mensajes de un estudiante específico
    @GetMapping("/estudiante/{idEstudiante}")
    public List<MensajeSemana> getByEstudiante(@PathVariable Integer idEstudiante) {
        return repository.findByIdEstudiante(idEstudiante);
    }

    // Listar mensajes de una semana específica
    @GetMapping("/semana/{semana}")
    public List<MensajeSemana> getBySemana(@PathVariable Integer semana) {
        return repository.findBySemana(semana);
    }

    // Crear un mensaje
    @PostMapping
    public MensajeSemana createMensaje(@RequestBody MensajeSemanaCrear dto) {
        MensajeSemana mensaje = MensajeSemana.builder()
                .semana(dto.getSemana())
                .idCurso(dto.getIdCurso())
                .idEstudiante(dto.getIdEstudiante())
                .mensaje(dto.getMensaje())
                .fechaCreacion(LocalDateTime.now())
                .build();
        return repository.save(mensaje);
    }
}
