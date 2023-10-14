package com.example.aula3ex1.service.repository;

import java.util.List;

import com.example.aula3ex1.dto.CategoriaCursoDTO;
import com.example.aula3ex1.dto.CategoriaInserirDTO;

public interface CategoriaCursoInterface {
    List<CategoriaCursoDTO> obterTodos();
    CategoriaInserirDTO inserir(CategoriaInserirDTO categoriaCursoDTO);
    CategoriaCursoDTO obterPorId(Long id);
    CategoriaCursoDTO editar(Long id, CategoriaCursoDTO categoriaCursoDTO);
    void excluir(Long id);
}
