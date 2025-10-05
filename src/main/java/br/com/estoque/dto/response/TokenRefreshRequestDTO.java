package br.com.estoque.dto.response;

import jakarta.validation.constraints.NotBlank;

public record TokenRefreshRequestDTO(

@NotBlank(message = "Token antigo é obrigatório")
String tokenAntigo) {

}