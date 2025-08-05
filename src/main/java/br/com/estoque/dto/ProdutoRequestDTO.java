package br.com.estoque.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProdutoRequestDTO {
    private String sku;
    private String nome;
    private Integer estoque;
    private String marca;
    private Integer quantidadeMinima;
    private Double preco;
    private Boolean status;
    private LocalDate dataValidade;
}
