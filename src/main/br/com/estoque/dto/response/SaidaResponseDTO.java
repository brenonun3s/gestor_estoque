package br.com.estoque.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estoque.model.entity.Produto;

public record SaidaResponseDTO(
  Produto produto,
  Integer quantidade,
  String motivo,
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  LocalDate data,
  String responsavel

) {

}
