package br.com.estoque.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estoque.enums.CategoriaProdutos;
import jakarta.validation.constraints.*;

public record ProdutoRequestDTO(
    @NotBlank(message = "SKU é obrigatório")
    String sku,

    @NotBlank(message = "Nome é obrigatório")
    String nome,
    
    @NotBlank(message = "Marca é obrigatória")
    String marca,

    @NotNull(message = "Quantidade mínima é obrigatória")
    @Min(value = 0, message = "Quantidade mínima não pode ser negativa")
    Integer quantidadeMinima,

    @NotNull(message = "Quantidade máxima é obrigatória")
    @Min(value = 0, message = "Quantidade mínima não pode ser negativa")
    @Max(value = 10000, message = "Quantidade máxima não pode ser superior a 10000")
    Integer quantidadeMaxima,

    @NotNull(message = "Categoria de Produtos é Obrigatório")
    CategoriaProdutos categoria,

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    Double preco,

    @NotNull(message = "Status é obrigatório")
    Boolean status,

    @NotNull(message = "Data de validade é obrigatória")
    @FutureOrPresent(message = "Data de validade deve ser hoje ou futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate dataValidade

) {}
