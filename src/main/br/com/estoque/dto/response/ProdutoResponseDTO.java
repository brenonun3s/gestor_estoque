package br.com.estoque.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estoque.model.enums.CategoriaProdutos;

public record ProdutoResponseDTO(
        UUID id,
        String sku,
        String nome,
        String marca,
        Integer quantidadeMinima,
        Integer quantidadeMaxima,
        Double preco,
        Boolean status,
        CategoriaProdutos categoria,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataValidade,
        Integer quantidade)
         {

}
