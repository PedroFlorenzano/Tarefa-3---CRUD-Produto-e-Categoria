package com.example.aula3ex1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursoInserirDTO {
    private String nome;
    private Integer cargaHoraria;
    private String nomeCategoriaCurso;
}
