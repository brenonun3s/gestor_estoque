package br.com.estoque.dto;

public record CnpjDto(
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
