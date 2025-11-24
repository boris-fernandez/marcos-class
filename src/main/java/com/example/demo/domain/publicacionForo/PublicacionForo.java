package com.example.demo.domain.publicacionForo;

import com.example.demo.domain.TemaForo.TemaForo;
import com.example.demo.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PublicacionForo", schema = "foro")
@Entity
@Builder
public class PublicacionForo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema", nullable = false)
    private TemaForo temaForo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private String contenido;

    private Date fechaPublicacion;
}
