package br.com.estoque.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class ProdutoResponseDTO {
    private UUID id;
    private String sku;
    private String nome;
    private Integer estoque;
    private String marca;
    private Integer quantidadeMinima;
    private Double preco;
    private Boolean status;
    private LocalDate dataValidade;
}
