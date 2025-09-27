package br.com.estoque.dto.response;

public record RelatorioProdutosDTO(
 String nomeProduto,
 Float preco,
 String categoria,
 String Sku
) {
 
}
