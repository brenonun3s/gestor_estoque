package br.com.estoque.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estoque.model.Produto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record SaidaRequestDTO(

 @NotNull(message = "SKU do produto para registrar saída é obrigatório") 
 Produto produto,

 @NotNull
 @Min(value = 1, message = "Mínimo para registrar saída é de 1")
 @Max(value = 10000, message = "Máximo para registrar saída é de 10000")
 Integer quantidade,

 @NotBlank(message = "Motivo da Saída é obrigatório") 
 String motivo,

 @PastOrPresent(message = "Data de Saída deve ser hoje ou passado")
 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
 @NotNull
 LocalDate data,

 @NotBlank(message = "Nome do responsável pela Saída é obrigatório")
 String responsavel) {
}
