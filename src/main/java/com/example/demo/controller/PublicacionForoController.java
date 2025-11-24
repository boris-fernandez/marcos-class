package com.example.demo.controller;

import com.example.demo.domain.publicacionForo.dto.CrearPublicacionForoDTO;
import com.example.demo.domain.publicacionForo.dto.PublicacionForoDTO;
import com.example.demo.service.PublicacionForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("publicacion")
public class PublicacionForoController {

    @Autowired
    private PublicacionForoService publicacionForoService;

    @PostMapping
    public ResponseEntity<PublicacionForoDTO> crearPublicacionForo(@RequestBody CrearPublicacionForoDTO crearPublicacionForoDTO){
        return ResponseEntity.ok(publicacionForoService.crearPublicacionForo(crearPublicacionForoDTO));
    }

    @GetMapping("foro/{idForo}")
    public ResponseEntity<List<PublicacionForoDTO>> listarPublicacionPorForo(@PathVariable Long idForo){
        return ResponseEntity.ok(publicacionForoService.listarPublicacionPorForo(idForo));
    }
}
