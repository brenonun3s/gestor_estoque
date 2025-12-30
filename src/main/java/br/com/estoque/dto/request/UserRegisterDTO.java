package br.com.estoque.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(


    @NotBlank(message = "Campo nome é obrigatório")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    String name,

    @NotBlank(message = "Campo Email é obrigatório")
    @Email(message = "Email deve ser válido")
    String email,

    @NotBlank(message = "Campo senha é obrigatório")
    @Size(min = 6, max = 100, message = "Sua senha deve ser entre 6 a 100 caracteres")
    String password

) {}