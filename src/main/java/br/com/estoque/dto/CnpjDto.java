package br.com.estoque.dto;

import org.hibernate.validator.constraints.br.CNPJ;

public record CnpjDTO(
  @CNPJ(message = "CNPJ inválido - 14 digitos")
  String cnpj,
  String razao_social,
  String nome_fantasia,
  String cnae_fiscal_descricao,
  String situacao_cadastral,
  Endereco endereco) {
 public record Endereco(
   String logradouro,
   String numero,
   String complemento,
   String bairro,
   String municipio,
   String uf,
   String cep) {
 }
}
