package br.com.estoque.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estoque.model.Produto;

public record EntradaResponseDTO(
  Produto produto,
  Integer quantidade,
  String motivo,
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate data,
  String responsavel) {
}