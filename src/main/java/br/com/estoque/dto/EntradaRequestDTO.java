package br.com.estoque.dto;

import java.time.LocalDate;

import br.com.estoque.model.Produto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record EntradaRequestDTO(
 @NotNull(message = "SKU do produto para registrar entrada é obrigatório")
 Produto produto,
 @Min(value = 1, message = "Mínimo para registrar entrada é de 1")
 @Max(value = 10000, message = "Máximo para registrar entrada é de 10000")
 Integer quantidade,
 @NotBlank(message = "Motivo de entrada é obrigatório")
 String motivo,
 @PastOrPresent(message = "Data de entrada deve ser hoje ou passado")
 LocalDate data,
 @NotBlank(message = "Nome do responsável pela entrada é obrigatório")
 String responsavel) {
} 
