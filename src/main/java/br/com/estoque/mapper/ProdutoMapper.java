package br.com.estoque.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.estoque.dto.ProdutoRequestDTO;
import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.dto.ProdutoUpdateDTO;
import br.com.estoque.model.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "estoques", ignore = true) 
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "id", ignore = true)
    Produto toEntity(ProdutoRequestDTO dto);

    ProdutoResponseDTO toResponseDTO(Produto produto);

    @Mapping(target = "estoques", ignore = true) 
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateProdutoFromDto(ProdutoUpdateDTO dto, @MappingTarget Produto produto);
}

