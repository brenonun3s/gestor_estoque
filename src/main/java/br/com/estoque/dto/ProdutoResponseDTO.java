package br.com.estoque.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ProdutoResponseDTO(
        String sku,
        String nome,
        Integer estoque,
        String marca,
        Integer quantidadeMinima,
        Double preco,
        Boolean status,
        String categoria,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataValidade) {

}
