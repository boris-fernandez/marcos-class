package com.example.demo.controller;

import com.example.demo.domain.TemaForo.dto.CrearTemaForoDTO;
import com.example.demo.domain.TemaForo.dto.TemaForoDTO;
import com.example.demo.service.TemaForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TemaForoController {

    @Autowired
    private TemaForoService temaForoService;

    @PostMapping
    public ResponseEntity<TemaForoDTO> crearTemaForo(@RequestBody CrearTemaForoDTO crearTemaForoDTO){
        return ResponseEntity.ok(temaForoService.crearTemaForo(crearTemaForoDTO));
    }

    @PatchMapping
    public ResponseEntity<TemaForoDTO> editarTemaForo(@PathVariable Long idForo , @RequestBody CrearTemaForoDTO temaForoDTO){
        return ResponseEntity.ok(temaForoService.editarTemaForo(idForo, temaForoDTO));
    }

    @GetMapping("curso/{idCurso}")
    public ResponseEntity<List<TemaForoDTO>> listarTemaForoPorCurso(@PathVariable Long idCurso){
        return ResponseEntity.ok(temaForoService.listarTemaForoPorCurso(idCurso));
    }

    @GetMapping("{idForo}")
    public ResponseEntity<TemaForoDTO> mostrarTemaForoPorId(@PathVariable Long idForo){
        return ResponseEntity.ok(temaForoService.mostrarTemaForoPorId(idForo));
    }

    @DeleteMapping("{idForo}")
    public ResponseEntity<Void> eliminarTemaForo(@PathVariable Long idForo){
        temaForoService.eliminarTemaForo(idForo);
        return ResponseEntity.ok().build();
    }
}
