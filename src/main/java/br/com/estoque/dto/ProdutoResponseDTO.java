package br.com.estoque.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estoque.enums.CategoriaProdutos;

public record ProdutoResponseDTO(
        String sku,
        String nome,
        String marca,
        Integer quantidadeMinima,
        Integer quantidadeMaxima,
        Double preco,
        Boolean status,
        CategoriaProdutos categoria,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataValidade) {

}
