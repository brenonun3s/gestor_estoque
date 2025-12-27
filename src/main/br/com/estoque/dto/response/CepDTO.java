package br.com.estoque.dto.response;

public record CepDTO(
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf
) {}
