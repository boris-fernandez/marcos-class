package com.example.demo.domain.curso;

import com.example.demo.domain.curso.dto.CursoCrear;
import com.example.demo.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Curso", schema = "cursos")
@Entity
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    private String nombre;

    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor", referencedColumnName = "idUsuario")
    private Usuario profesor;

    private Boolean estado;

    public void cambiarEstado(){
        this.estado = (this.estado == null) ? false : !this.estado;
    }

    public void actualizar(CursoCrear crear, Usuario usuario){
        if (crear.nombre() != null) this.nombre = crear.nombre();
        if (crear.descripcion() != null) this.descripcion = crear.descripcion();
        if(usuario != null) this.profesor = usuario;
    }
}
