package br.com.estoque.dto.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.estoque.dto.request.SaidaRequestDTO;
import br.com.estoque.dto.response.SaidaResponseDTO;
import br.com.estoque.model.entity.Produto;
import br.com.estoque.model.entity.Saida;
import br.com.estoque.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring")
public abstract class SaidaMapper {

    @Autowired
    protected ProdutoRepository produtoRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produto", source = "produtoId")
    public abstract Saida toEntity(SaidaRequestDTO dto);

    public abstract SaidaResponseDTO toResponseDTO(Saida saida);

    protected Produto map(UUID produtoId) {
        return produtoRepository.findById(produtoId)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }
}

