package br.com.estoque.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

public record ProdutoRequestDTO(
    @NotBlank(message = "SKU é obrigatório")
    String sku,

    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotNull(message = "Estoque é obrigatório")
    @Min(value = 0, message = "Estoque não pode ser negativo")
    Integer estoque,

    @NotBlank(message = "Marca é obrigatória")
    String marca,

    @NotNull(message = "Quantidade mínima é obrigatória")
    @Min(value = 0, message = "Quantidade mínima não pode ser negativa")
    Integer quantidadeMinima,

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    Double preco,

    @NotNull(message = "Status é obrigatório")
    Boolean status,

    @NotNull(message = "Data de validade é obrigatória")
    @FutureOrPresent(message = "Data de validade deve ser hoje ou futura")
    LocalDate dataValidade

) {}
