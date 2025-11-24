package com.example.demo.controller;

import com.example.demo.domain.CalificacionTarea.dto.CalificacionTareaDTO;
import com.example.demo.domain.CalificacionTarea.dto.CrearCalificacionTareaDTO;
import com.example.demo.service.CalificacionTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("calificacion")
public class CalificacionTareaController {

    @Autowired
    private CalificacionTareaService calificacionTareaService;

    @PostMapping
    public ResponseEntity<CalificacionTareaDTO> crearCalificacionTarea(@RequestBody CrearCalificacionTareaDTO crearCalificacionTareaDTO){
        return ResponseEntity.ok(calificacionTareaService.crearCalificacionTarea(crearCalificacionTareaDTO));
    }

    @PatchMapping("{idCalificacion}")
    public ResponseEntity<CalificacionTareaDTO> actualizarCalificacionTarea(@RequestBody CrearCalificacionTareaDTO crearCalificacionTareaDTO, @PathVariable Long idCalificacion){
        return ResponseEntity.ok(calificacionTareaService.actualizarCalificacionTarea(idCalificacion, crearCalificacionTareaDTO));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<CalificacionTareaDTO> calificacionPorUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.ok(calificacionTareaService.calificacionPorUsuario(idUsuario));
    }
}
