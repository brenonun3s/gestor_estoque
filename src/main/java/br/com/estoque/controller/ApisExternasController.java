package br.com.estoque.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.response.CepDTO;
import br.com.estoque.dto.response.CnpjDTO;
import br.com.estoque.service.CepClient;
import br.com.estoque.service.CnpjClient;

@RestController
@RequestMapping("/api")
public class ApisExternasController {

    private final CnpjClient cnpjClient;
    private final CepClient cepClient;

    public ApisExternasController(CnpjClient cnpjClient, CepClient cepClient) {
        this.cnpjClient = cnpjClient;
        this.cepClient = cepClient;
    }

    @GetMapping("/cnpj/{cnpj}")
    public CnpjDTO buscarCnpj(@PathVariable String cnpj) {
        return cnpjClient.consultar(cnpj);
    }

    @GetMapping("/cep/{cep}")
    public CepDTO buscarCep(@PathVariable String cep) {
        return cepClient.buscarCep(cep);
    }
}
