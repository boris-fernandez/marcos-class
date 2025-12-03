package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeSemanaCrear {
    private Integer semana;
    private Integer idCurso;
    private Integer idEstudiante;
    private String mensaje;
}
