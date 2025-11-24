package com.example.demo.controller;

import com.example.demo.domain.material.dto.CrearMaterialDTO;
import com.example.demo.domain.material.dto.MaterialDTO;
import com.example.demo.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<MaterialDTO> creaMaterial(@RequestBody @Valid CrearMaterialDTO crearMaterialDTO){
        return ResponseEntity.ok(materialService.crearMaterial(crearMaterialDTO));
    }

    @PatchMapping("{id}")
    public ResponseEntity<MaterialDTO> editarMaterial(@RequestBody @Valid CrearMaterialDTO crearMaterialDTO, @PathVariable Long id){
        return ResponseEntity.ok(materialService.editarMaterial(id, crearMaterialDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarMaterial(@PathVariable Long id){
        materialService.eliminarMaterial(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("curso/{idCurso}")
    public ResponseEntity<List<MaterialDTO>> listarMaterialesPorCurso(@PathVariable Long idCurso){
        return ResponseEntity.ok(materialService.listarMaterialesPorCurso(idCurso));
    }

    @GetMapping("{idMaterial}")
    public ResponseEntity<MaterialDTO> materialPorId(@PathVariable Long idMaterial){
        return ResponseEntity.ok(materialService.materialPorId(idMaterial));
    }
}
