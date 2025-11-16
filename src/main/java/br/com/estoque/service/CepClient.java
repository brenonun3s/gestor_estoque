package br.com.estoque.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import br.com.estoque.dto.response.CepDTO;

@Service
public class CepClient {

  private final RestTemplate http;

  public CepClient(RestTemplate http) {
    this.http = http;
}

public CepDTO buscarCep(String cep) {
    if (!cep.matches("\\d{8}")) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, "CEP inválido. Deve conter 8 dígitos numéricos.");
    }

    String url = "https://viacep.com.br/ws/{cep}/json/";
    ResponseEntity<CepDTO> resp = http.getForEntity(url, CepDTO.class, cep);

    if (resp.getBody() == null || resp.getBody().cep() == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CEP não encontrado");
    }

    return resp.getBody();
}
}
