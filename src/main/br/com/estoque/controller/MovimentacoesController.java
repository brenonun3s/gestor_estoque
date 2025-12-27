package br.com.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.response.MovimentacoesResponseDTO;
import br.com.estoque.service.MovimentacaoService;

@RequestMapping("/movimentacoes")
@RestController
public class MovimentacoesController {

    @Autowired
    MovimentacaoService service;

    @GetMapping
    public ResponseEntity<List<MovimentacoesResponseDTO>> listar() {
        List<MovimentacoesResponseDTO> movimentacoes = service.listarMovimentacoes();
        return ResponseEntity.ok().body(movimentacoes);
    }

}
