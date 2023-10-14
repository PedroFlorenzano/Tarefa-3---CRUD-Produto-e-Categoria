package com.example.aula3ex1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.aula3ex1.models.Curso;


public interface CursoRepository extends JpaRepository<Curso,Long > 
{
    List<Curso> findByNomeLike(String nome);
}
