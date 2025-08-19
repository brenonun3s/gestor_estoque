package br.com.estoque.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estoque.enums.CategoriaProdutos;
import jakarta.validation.constraints.*;

public record ProdutoUpdateDTO(
    @NotBlank(message = "Nome não pode ser vazio")
    String nome,

    @NotBlank(message = "Marca não pode ser vazio")
    String marca,

    @NotNull(message = "Estoque é obrigatório")
    @Min(value = 0, message = "Estoque não pode ser negativo")
    Integer estoque,

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    Double preco,

    @NotNull(message = "Status é obrigatório")
    Boolean status,

    @NotBlank(message = "SKU não pode ser vazio")
    String sku,

    @NotNull(message = "Quantidade mínima é obrigatória")
    @Min(value = 0, message = "Quantidade mínima não pode ser negativa")
    Integer quantidadeMinima,

    @NotNull(message = "Data de validade é obrigatória")
    @FutureOrPresent(message = "Data de validade deve ser hoje ou no futuro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate dataValidade,

    @NotNull(message = "Categoria de Produtos é Obrigatório")
    CategoriaProdutos categoria



) {}
