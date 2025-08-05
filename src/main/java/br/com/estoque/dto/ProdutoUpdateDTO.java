package br.com.estoque.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProdutoUpdateDTO {
    private String nome;
    private Integer estoque;
    private Double preco;
    private Boolean status;
    private LocalDate dataValidade;
}
