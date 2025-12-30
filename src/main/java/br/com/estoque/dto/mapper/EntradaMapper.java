package br.com.estoque.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.estoque.dto.request.EntradaRequestDTO;
import br.com.estoque.dto.response.EntradaResponseDTO;
import br.com.estoque.model.entity.Entrada;

@Mapper(componentModel = "spring")
public interface EntradaMapper {

    @Mapping(target = "id", ignore = true)
    Entrada toEntity(EntradaRequestDTO dto);

    EntradaResponseDTO toResponseDTO(Entrada entrada);

}

