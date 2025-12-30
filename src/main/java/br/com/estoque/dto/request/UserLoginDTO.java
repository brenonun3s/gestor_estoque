package br.com.estoque.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(

  @NotBlank(message = "Campo Email é obrigatório") @Email(message = "Campo email deve estar no formato válido") String email,

  @NotBlank(message = "Campo Senha é obrigatório") String password

) {
}