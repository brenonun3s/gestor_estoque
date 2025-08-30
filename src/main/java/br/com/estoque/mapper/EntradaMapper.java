package br.com.estoque.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.estoque.dto.EntradaRequestDTO;
import br.com.estoque.dto.EntradaResponseDTO;
import br.com.estoque.model.Entrada;

@Mapper(componentModel = "spring")
public interface EntradaMapper {

    @Mapping(target = "id", ignore = true)
    Entrada toEntity(EntradaRequestDTO dto);

    EntradaResponseDTO toResponseDTO(Entrada entrada);

}

