package br.com.estoque.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.CnpjDto;
import br.com.estoque.dto.EstadoDto;
import br.com.estoque.dto.MunicipioDto;
import br.com.estoque.service.CnpjClient;
import br.com.estoque.service.IbgeClient;

@RestController
@RequestMapping("/api")
public class ApisExternasTeste {

  private final CnpjClient cnpjClient;
  private final IbgeClient ibgeClient;

  public ApisExternasTeste(CnpjClient cnpjClient, IbgeClient ibgeClient) {
    this.cnpjClient = cnpjClient;
    this.ibgeClient = ibgeClient;
  }

  @GetMapping("/cnpj/{cnpj}")
  public CnpjDto buscarCnpj(@PathVariable String cnpj) {
    return cnpjClient.consultar(cnpj);
  }

  @GetMapping("/ibge/estados")
  public List<EstadoDto> estados() {
    return ibgeClient.listarEstados()
      .stream()
      .sorted(Comparator.comparing(EstadoDto::sigla))
      .toList();
  }

  @GetMapping("/ibge/estados/{uf}/municipios")
  public List<MunicipioDto> municipios(@PathVariable String uf) {
    return ibgeClient.listarMunicipiosPorUf(uf)
      .stream()
      .sorted(Comparator.comparing(MunicipioDto::nome))
      .toList();
  }
}
