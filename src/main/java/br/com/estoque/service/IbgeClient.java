package br.com.estoque.service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import br.com.estoque.dto.MunicipioDto;

@Service
public class IbgeClient {

  private final RestTemplate http;

  public IbgeClient(RestTemplate http) {
    this.http = http;
  }

  public List<MunicipioDto> listarMunicipiosPorUf(String uf) {
    String ufUp = uf.toUpperCase(Locale.ROOT);
    if (!ufUp.matches("^[A-Z]{2}$")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UF inválida");
    }
    String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/{uf}/municipios";
    ResponseEntity<MunicipioDto[]> resp = http.getForEntity(url, MunicipioDto[].class, ufUp);
    return Arrays.asList(Objects.requireNonNull(resp.getBody()));
  }
}
