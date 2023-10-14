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
import com.example.aula3ex1.dto.CursoDTO;
import com.example.aula3ex1.dto.CursoInserirDTO;
import com.example.aula3ex1.service.CursoServiceImpl;

@RestController
@RequestMapping("/curso")
public class CursoController {
 
    private CursoServiceImpl cursoService;

    public CursoController(CursoServiceImpl cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<CursoDTO> obterTodos(){
        return cursoService.obterTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoInserirDTO inserir(@RequestBody CursoInserirDTO cursoDTO){
        return cursoService.inserir(cursoDTO);
    }

    @GetMapping("{id}")
    public CursoDTO obterPorId(@PathVariable Long id){
        return cursoService.obterPorId(id);
    }

    @PutMapping("{id}")
    public CursoDTO editar(@PathVariable Long id, @RequestBody CursoDTO cursoDTO){
        return cursoService.editar(id, cursoDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        this.cursoService.excluir(id);
    }
}
