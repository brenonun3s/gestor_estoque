package br.com.estoque.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.estoque.dto.request.ProdutoRequestDTO;
import br.com.estoque.dto.response.ProdutoResponseDTO;
import br.com.estoque.dto.response.ProdutoUpdateDTO;
import br.com.estoque.model.entity.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estoque", ignore = true)
    Produto toEntity(ProdutoRequestDTO dto);

    @Mapping(target = "quantidade", ignore = true)
    ProdutoResponseDTO toResponseDTO(Produto produto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estoque", ignore = true)
    void updateProdutoFromDto(ProdutoUpdateDTO dto, @MappingTarget Produto produto);
}

