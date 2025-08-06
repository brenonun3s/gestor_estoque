package br.com.estoque.mapper;

import br.com.estoque.dto.ProdutoRequestDTO;
import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.dto.ProdutoUpdateDTO;
import br.com.estoque.model.Produto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "id", ignore = true)
    Produto toEntity(ProdutoRequestDTO dto);

    ProdutoResponseDTO toResponseDTO(Produto produto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateProdutoFromDto(ProdutoUpdateDTO dto, @MappingTarget Produto produto);

}