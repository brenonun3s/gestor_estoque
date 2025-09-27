package br.com.estoque.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record MovimentacoesDTO(
 Integer quantidade,
 String motivo,
 String responsavel,
 String skuProduto,
 String nomeProduto,
 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
 LocalDate data,
 String tipo
 )
 {}