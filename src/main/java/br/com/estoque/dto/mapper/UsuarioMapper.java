package br.com.estoque.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.estoque.dto.request.UserRegisterDTO;
import br.com.estoque.dto.response.UserResponseDTO;
import br.com.estoque.model.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

 @Mapping(target = "id", ignore = true)
 Usuario toEntity(UserRegisterDTO dto);

 UserResponseDTO toResponseDto(Usuario user);

}