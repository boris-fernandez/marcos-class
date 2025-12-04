package com.example.demo.controller;

import com.example.demo.domain.curso.dto.CursoCrear;
import com.example.demo.domain.curso.dto.CursoDTO;
import com.example.demo.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDTO> crearCurso(@RequestBody @Valid CursoCrear crear){
        return ResponseEntity.ok(cursoService.crearCurso(crear));
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos(){
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id){
        cursoService.eliminarCurso(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CursoDTO> actualizarCurso(@RequestBody @Valid CursoCrear crear, @PathVariable Long id){
        return ResponseEntity.ok(cursoService.actualizarCurso(crear, id));
    }
}
