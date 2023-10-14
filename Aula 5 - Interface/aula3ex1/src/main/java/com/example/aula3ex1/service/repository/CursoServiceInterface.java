package com.example.aula3ex1.service.repository;

import java.util.List;

import com.example.aula3ex1.dto.CursoDTO;
import com.example.aula3ex1.dto.CursoInserirDTO;

public interface CursoServiceInterface {
    List<CursoDTO> obterTodos();
    CursoInserirDTO inserir(CursoInserirDTO cursoDTO);
    CursoDTO obterPorId(Long id);
    CursoDTO editar(Long id, CursoDTO cursoDTO);
    void excluir(Long id);
}
