package br.com.estoque.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.estoque.dto.SaidaRequestDTO;
import br.com.estoque.dto.SaidaResponseDTO;
import br.com.estoque.model.Saida;

@Mapper(componentModel = "spring")
public interface SaidaMapper {

    @Mapping(target = "id", ignore = true)
    Saida toEntity(SaidaRequestDTO dto);

    SaidaResponseDTO toResponseDTO(Saida saida);
}

