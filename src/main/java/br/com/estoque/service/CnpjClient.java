package br.com.estoque.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import br.com.estoque.dto.CnpjDTO;


@Service
public class CnpjClient {

 private static final String URL = "https://brasilapi.com.br/api/cnpj/v1/{cnpj}";
 private final RestTemplate http;

 public CnpjClient(RestTemplate http) {
  this.http = http;
 }

 public CnpjDTO consultar(String cnpj) {
  String digits = cnpj.replaceAll("\\D", "");
  if (!digits.matches("\\d{14}")) {
   throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CNPJ inválido");
  }

  try {
   return http.getForObject(URL, CnpjDTO.class, digits);
  } catch (HttpClientErrorException.NotFound e) {
   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CNPJ não encontrado");
  } catch (HttpStatusCodeException e) {
   throw new ResponseStatusException(e.getStatusCode(), "Erro BrasilAPI: " + e.getResponseBodyAsString());
  } catch (ResourceAccessException e) {
   throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Timeout consultando BrasilAPI");
  }
 }
}
