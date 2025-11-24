package com.example.demo.service;

import com.example.demo.domain.curso.Curso;
import com.example.demo.domain.curso.CursoRepository;
import com.example.demo.domain.material.Material;
import com.example.demo.domain.material.MaterialRepository;
import com.example.demo.domain.material.dto.CrearMaterialDTO;
import com.example.demo.domain.material.dto.MaterialDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //CREAR MATERIAL
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public MaterialDTO crearMaterial(CrearMaterialDTO crearMaterialDTO){
        Material material = Material.builder()
                .curso(cursoRepository.findByNombre(crearMaterialDTO.curso()).orElseThrow(()-> new RuntimeException("No existe un curso con este nombre: " + crearMaterialDTO.curso())))
                .titulo(crearMaterialDTO.titulo())
                .tipo(crearMaterialDTO.tipo())
                .urlArchivo(crearMaterialDTO.urlArchivo())
                .fechaSubida(new Date())
                .build();
        materialRepository.save(material);
        return new MaterialDTO(material);
    }

    //EDITAR MATERIAL
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public MaterialDTO editarMaterial(Long id, CrearMaterialDTO editarMaterialDTO){
        Material material = materialRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe un material con este ID: " + id));
        Curso curso = cursoRepository.findByNombre(editarMaterialDTO.curso()).orElseThrow(()-> new RuntimeException("No existe un curso con este nombre: " + editarMaterialDTO.curso()));
        material.editarMaterial(editarMaterialDTO,curso);
        return new MaterialDTO(material);
    }

    //ELIMINAR MATERIAL
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR')")
    public void eliminarMaterial(Long id){
        Material material = materialRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe un material con este ID: " + id));
        materialRepository.deleteById(id);
    }

    //LISTAR MATERIAL
    public List<MaterialDTO> listarMaterialesPorCurso(Long idCurso) {
        if (!cursoRepository.existsById(idCurso)) {
            throw new RuntimeException("No existe un curso con este id: " + idCurso);
        }

        return materialRepository.findAllByCurso_IdCurso(idCurso)
                .stream()
                .map(MaterialDTO::new)
                .toList();
    }

    //MOSTRAR MATERIAL POR ID
    public MaterialDTO materialPorId(Long idMaterial){
        Material material = materialRepository.findById(idMaterial).orElseThrow(() -> new RuntimeException("No existe un material con este ID: " + idMaterial));
        return new MaterialDTO(material);
    }
}
