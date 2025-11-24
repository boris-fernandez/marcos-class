package com.example.demo.domain.material;

import com.example.demo.domain.curso.Curso;
import com.example.demo.domain.material.dto.CrearMaterialDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Material", schema = "materiales")
@Entity
@Builder
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaterial;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    private String titulo;

    private String tipo;

    private String urlArchivo;

    private Date fechaSubida;

    public void editarMaterial(CrearMaterialDTO updateMaterial, Curso curso){
        if(curso != null) setCurso(curso);
        if (updateMaterial.tipo()!= null) setTipo(updateMaterial.tipo());
        if (updateMaterial.titulo() != null) setTitulo(updateMaterial.titulo());
        if (updateMaterial.urlArchivo() != null) setUrlArchivo(updateMaterial.urlArchivo());
    }
}
