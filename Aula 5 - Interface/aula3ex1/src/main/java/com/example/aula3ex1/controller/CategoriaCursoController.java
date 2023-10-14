package com.example.aula3ex1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula3ex1.dto.CategoriaCursoDTO;
import com.example.aula3ex1.dto.CategoriaInserirDTO;
import com.example.aula3ex1.service.CategoriaServeImpl;

@RestController
@RequestMapping("/categoria")
public class CategoriaCursoController {
    
    private CategoriaServeImpl categoriaCursoService;

    public CategoriaCursoController(CategoriaServeImpl categoriaCursoService) {
        this.categoriaCursoService = categoriaCursoService;
    }

    @GetMapping
    public List<CategoriaCursoDTO> obterTodos() {
        return categoriaCursoService.obterTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaInserirDTO inserir(@RequestBody CategoriaInserirDTO categoriaCursoDTO) {
        return categoriaCursoService.inserir(categoriaCursoDTO);
    }

    @GetMapping("{id}")
    public CategoriaCursoDTO obterPorId(@PathVariable Long id) {
        return categoriaCursoService.obterPorId(id);
    }

    @PutMapping("{id}")
    public CategoriaCursoDTO editar(@PathVariable Long id, @RequestBody CategoriaCursoDTO categoriaCursoDTO) {
       return categoriaCursoService.editar(id, categoriaCursoDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        this.categoriaCursoService.excluir(id);
    }

}
