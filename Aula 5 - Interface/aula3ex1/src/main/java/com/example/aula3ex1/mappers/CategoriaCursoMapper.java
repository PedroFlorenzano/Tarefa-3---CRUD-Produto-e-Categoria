package com.example.aula3ex1.mappers;

import com.example.aula3ex1.dto.CategoriaCursoDTO;
import com.example.aula3ex1.dto.CategoriaInserirDTO;
import com.example.aula3ex1.models.CategoriaCurso;

public class CategoriaCursoMapper {
    
    public static CategoriaCursoDTO convertCategoriaToDTO(CategoriaCurso categoria) {
        return new CategoriaCursoDTO(categoria.getId(),
                categoria.getNome());
    }

    public static CategoriaCurso convertCategoriaDTOToEntity(CategoriaCursoDTO categoria) {
        return new CategoriaCurso(categoria.getId(),
                categoria.getNome(),
                null);
    }

    public static CategoriaCurso convertCategoriaDTOToEntity(CategoriaCursoDTO categoria, Long id, CategoriaCurso categoriaCurso) {
        return new CategoriaCurso(id,
                categoria.getNome(),
                categoriaCurso.getCursos());
    }

    public static CategoriaCurso convertCategoriaInserirDTOToEntity(CategoriaInserirDTO categoria) {
        return new CategoriaCurso(null,
                categoria.getNome(),
                null);
    }
}
