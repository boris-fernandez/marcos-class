package com.example.demo.controller;

import com.example.demo.domain.Tarea.dto.CrearTareaDTO;
import com.example.demo.domain.Tarea.dto.TareDTO;
import com.example.demo.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @PostMapping
    public ResponseEntity<TareDTO> crearTarea(@RequestBody CrearTareaDTO crearTareaDTO){
        return ResponseEntity.ok(tareaService.crearTarea(crearTareaDTO));
    }

    @DeleteMapping("{idTarea}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idTarea){
        tareaService.eliminar(idTarea);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{idTarea}")
    public ResponseEntity<TareDTO> editarTarea(@PathVariable Long idTarea, @RequestBody CrearTareaDTO crearTareaDTO){
        return ResponseEntity.ok(tareaService.editarTarea(idTarea, crearTareaDTO));
    }

    @GetMapping("curso/{idCurso}")
    public ResponseEntity<List<TareDTO>> listarTareas(@PathVariable Long idCurso){
        return ResponseEntity.ok(tareaService.listarTareas(idCurso));
    }

    @GetMapping("{idTarea}")
    public ResponseEntity<TareDTO> tareaPorId(@PathVariable Long idTarea){
        return ResponseEntity.ok(tareaService.tareaPorId(idTarea));
    }
}
