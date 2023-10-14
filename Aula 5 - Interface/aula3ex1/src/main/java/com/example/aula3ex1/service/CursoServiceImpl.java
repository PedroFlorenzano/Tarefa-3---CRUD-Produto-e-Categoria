package com.example.aula3ex1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.aula3ex1.dto.CursoDTO;
import com.example.aula3ex1.dto.CursoInserirDTO;
import com.example.aula3ex1.exceptions.RegraNegocioException;
import com.example.aula3ex1.mappers.CursoMapper;
import com.example.aula3ex1.models.CategoriaCurso;
import com.example.aula3ex1.models.Curso;
import com.example.aula3ex1.repository.CategoriaRepository;
import com.example.aula3ex1.repository.CursoRepository;
import com.example.aula3ex1.service.repository.CursoServiceInterface;

@Service
public class CursoServiceImpl implements CursoServiceInterface {

    private final CursoRepository cursoRepository;
    private final CategoriaRepository categoriaRepository;
   
    public CursoServiceImpl(CursoRepository cursoRepository, CategoriaRepository categoriaRepository){
        this.cursoRepository = cursoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CursoDTO> obterTodos() {
        List<CursoDTO> cursoList = cursoRepository.findAll().stream().map(
            (Curso curso) -> {
                return CursoDTO.builder()
                    .id(curso.getId())
                    .nome(curso.getNome())
                    .cargaHoraria(curso.getCargaHoraria())
                    .nomeCategoriaCurso(curso.getCategoriaCurso().getNome() != null ? 
                                        curso.getCategoriaCurso().getNome() : "Curso não possui uma categoria cadastrada!.")
                    .build();
            }
        ).collect(Collectors.toList());
        return cursoList;
    }

    @Override
    public CursoInserirDTO inserir(CursoInserirDTO cursoDTO) {

        CategoriaCurso categoriaCurso = categoriaRepository.findByNome(cursoDTO.getNomeCategoriaCurso()).orElseThrow(() -> new RegraNegocioException("Não foi possível encontrar a categoria pelo nome!."));

        CursoInserirDTO curso = CursoInserirDTO.builder()
                        .nome(cursoDTO.getNome())
                        .cargaHoraria(cursoDTO.getCargaHoraria())
                        .nomeCategoriaCurso(cursoDTO.getNomeCategoriaCurso())
                        .build();

        cursoRepository.save(CursoMapper.convertCursoInserirDTOToEntity(cursoDTO, categoriaCurso));

        return curso;
    }

    @Override
    public CursoDTO obterPorId(Long id) {
        return cursoRepository.findById(id).map(
            (Curso curso) -> {
                return CursoDTO.builder()
                               .id(curso.getId())
                               .nome(curso.getNome())
                               .cargaHoraria(curso.getCargaHoraria())
                               .nomeCategoriaCurso(curso.getCategoriaCurso().getNome() != null ?
                               curso.getCategoriaCurso().getNome() : "Curso não possui uma categoria cadastrada!.")
                               .build();
            }
        ).orElseThrow(() -> new RegraNegocioException("Id do curso não encontrado na base!."));
    }

    @Override
    public CursoDTO editar(Long id, CursoDTO cursoDTO) {
        CursoDTO oldCurso = this.obterPorId(id);

        CategoriaCurso categoriaCurso = categoriaRepository.findByNome(cursoDTO.getNomeCategoriaCurso()).orElseThrow(() -> new RegraNegocioException("Não foi possível encontrar a categoria pelo nome!."));

        CursoDTO curso = CursoDTO.builder()
                        .id(oldCurso.getId())
                        .nome(cursoDTO.getNome())
                        .cargaHoraria(cursoDTO.getCargaHoraria())
                        .nomeCategoriaCurso(categoriaCurso.getNome())
                        .build();

        cursoRepository.save(CursoMapper.convertCursoDTOToEntity(cursoDTO, categoriaCurso, oldCurso.getId()));

        return curso;
    }

    @Override
    public void excluir(Long id) {
        this.cursoRepository.deleteById(id);
    }
}
