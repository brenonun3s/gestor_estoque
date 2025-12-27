package br.com.estoque.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.estoque.dto.request.SaidaRequestDTO;
import br.com.estoque.dto.response.SaidaResponseDTO;
import br.com.estoque.model.entity.Saida;

@Mapper(componentModel = "spring")
public interface SaidaMapper {

    @Mapping(target = "id", ignore = true)
    Saida toEntity(SaidaRequestDTO dto);

    SaidaResponseDTO toResponseDTO(Saida saida);
}

