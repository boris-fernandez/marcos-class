package com.example.demo.controller;

import com.example.demo.domain.inscripcion.dto.InscripcionCrear;
import com.example.demo.domain.inscripcion.dto.InscripcionDTO;
import com.example.demo.service.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<InscripcionDTO> crearInscripcion(@RequestBody @Valid InscripcionCrear crear){
        return ResponseEntity.ok().body(inscripcionService.crearInscripcion(crear));
    }

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> listarInscripciones(){
        return ResponseEntity.ok().body(inscripcionService.listarInscripciones());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> listarInscripciones(@PathVariable Long id){
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("usuario/{idUsuario}")
    public ResponseEntity<List<InscripcionDTO>> listarIncripcionesPorUsuario(@PathVariable Long id){
        return ResponseEntity.ok(inscripcionService.listarIncripcionesPorUsuario(id));
    }

}
