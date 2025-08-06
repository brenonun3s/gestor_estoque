package br.com.estoque.dto;

import java.time.LocalDate;

public record ProdutoResponseDTO(
        String sku,
        String nome,
        Integer estoque,
        String marca,
        Integer quantidadeMinima,
        Double preco,
        Boolean status,
        LocalDate dataValidade) {

}
