package com.example.aula3ex1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.aula3ex1.dto.CategoriaCursoDTO;
import com.example.aula3ex1.dto.CategoriaInserirDTO;
import com.example.aula3ex1.exceptions.RegraNegocioException;
import com.example.aula3ex1.mappers.CategoriaCursoMapper;
import com.example.aula3ex1.models.CategoriaCurso;
import com.example.aula3ex1.repository.CategoriaRepository;
import com.example.aula3ex1.service.repository.CategoriaCursoInterface;

@Service
public class CategoriaServeImpl implements CategoriaCursoInterface{
    
    private CategoriaRepository categoriaRepository;

    public CategoriaServeImpl(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaInserirDTO inserir(CategoriaInserirDTO categoriaInserirDTO) {
        CategoriaInserirDTO categoria = CategoriaInserirDTO.builder()
                                      .nome(categoriaInserirDTO.getNome())
                                      .build();

        categoriaRepository.save(CategoriaCursoMapper.convertCategoriaInserirDTOToEntity(categoriaInserirDTO));

        return categoria;
    }

    @Override
    public List<CategoriaCursoDTO> obterTodos() {
        List<CategoriaCursoDTO> categoriaList = categoriaRepository.findAll().stream().map(
            (CategoriaCurso categoria) -> {
                return CategoriaCursoDTO.builder()
                    .id(categoria.getId())
                    .nome(categoria.getNome())
                    .build();
            }
        ).collect(Collectors.toList());
        return categoriaList;
    }

    @Override
    public CategoriaCursoDTO obterPorId(Long id) {
        return categoriaRepository.findById(id).map(
            (CategoriaCurso categoria) -> {
                return CategoriaCursoDTO.builder()
                    .id(categoria.getId())
                    .nome(categoria.getNome())
                    .build();       
            }
        ).orElseThrow(() -> new RegraNegocioException("Id da categoria não encontrada dentro da base!."));
    }

    @Override
    public CategoriaCursoDTO editar(Long id, CategoriaCursoDTO categoriaCursoDTO) {
        CategoriaCurso oldCategoria = categoriaRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Id da categoria não encontrada dentro da base!."));

        CategoriaCursoDTO categoriaCurso = CategoriaCursoDTO.builder()
                                            .id(oldCategoria.getId())
                                            .nome(categoriaCursoDTO.getNome())
                                            .build();

        categoriaRepository.save(CategoriaCursoMapper.convertCategoriaDTOToEntity(categoriaCursoDTO, oldCategoria.getId(), oldCategoria));

        return categoriaCurso;
    }

    @Override
    public void excluir(Long id) {
        this.categoriaRepository.deleteById(id);
    }


}
