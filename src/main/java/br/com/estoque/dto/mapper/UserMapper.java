package br.com.estoque.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.estoque.dto.request.UserRegisterDTO;
import br.com.estoque.dto.response.UserResponseDTO;
import br.com.estoque.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

 @Mapping(target = "id", ignore = true)
 User toEntity(UserRegisterDTO dto);

 UserResponseDTO toResponseDto(User user);

}