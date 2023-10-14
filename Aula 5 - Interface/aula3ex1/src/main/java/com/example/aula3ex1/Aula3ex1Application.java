package com.example.aula3ex1;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.aula3ex1.models.CategoriaCurso;
import com.example.aula3ex1.models.Curso;
import com.example.aula3ex1.repository.CategoriaRepository;
import com.example.aula3ex1.repository.CursoRepository;

@SpringBootApplication
public class Aula3ex1Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Aula3ex1Application.class, args);
	}

	@Bean
	public CommandLineRunner init(@Autowired CategoriaRepository categoriaCursoRepository, @Autowired CursoRepository cursoRepository){
		return args -> {

			CategoriaCurso categoria1 = new CategoriaCurso(null, "Tecnologia", null);
			CategoriaCurso categoria2 = new CategoriaCurso(null, "Engenharia", null);
			CategoriaCurso categoria3 = new CategoriaCurso(null, "Saude", null);

			categoriaCursoRepository.save(categoria1);
			categoriaCursoRepository.save(categoria2);
			categoriaCursoRepository.save(categoria3);

			CategoriaCurso categoria1Encontrada = categoriaCursoRepository.findById(1L).get();
			CategoriaCurso categoria2Encontrada = categoriaCursoRepository.findById(2L).get();
			CategoriaCurso categoria3Encontrada = categoriaCursoRepository.findById(3L).get();

			Curso curso1 = new Curso(null, "Análise e Desenvolvimento de Sistemas", 200, categoria1Encontrada);
			Curso curso2 = new Curso(null, "Engenharia Civil", 100, categoria2Encontrada);
			Curso curso3 = new Curso(null, "Medicina", 250, categoria3Encontrada);

			cursoRepository.save(curso1);
			cursoRepository.save(curso2);
			cursoRepository.save(curso3);

			List<Curso> tecnologiaList = new ArrayList<>();
			tecnologiaList.add(curso1);
			
			List<Curso> culinariaList = new ArrayList<>();
			culinariaList.add(curso2);

			List<Curso> jogoList = new ArrayList<>();
			jogoList.add(curso3);

			categoria1Encontrada.setCursos(tecnologiaList);
			categoria2Encontrada.setCursos(culinariaList);
			categoria3Encontrada.setCursos(jogoList);

			categoriaCursoRepository.save(categoria1Encontrada);
			categoriaCursoRepository.save(categoria2Encontrada);
			categoriaCursoRepository.save(categoria3Encontrada);
		};
	}

}
