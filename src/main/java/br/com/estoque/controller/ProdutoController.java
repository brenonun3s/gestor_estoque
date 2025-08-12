package br.com.estoque.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.ProdutoRequestDTO;
import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.dto.ProdutoUpdateDTO;
import br.com.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProdutoController {

  private final ProdutoService service;

  @GetMapping("/listar-produtos")
  public ResponseEntity<List<ProdutoResponseDTO>> listar() {
    List<ProdutoResponseDTO> produtos = service.listarProdutos();
    return ResponseEntity.ok(produtos);
  }

  @PutMapping("atualizar-produto/{id}")
  public ResponseEntity<Void> atualizarProduto(@PathVariable UUID id,
      @RequestBody @Valid ProdutoUpdateDTO dto) {
    service.atualizarProduto(id, dto);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/deletar-produto/{id}")
  public ResponseEntity<Void> deletarProduto(@PathVariable UUID id) {
    service.deletarProduto(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/cadastrar-produtos")
  public ResponseEntity<ProdutoResponseDTO> cadastrar(@Valid @RequestBody ProdutoRequestDTO dto) {
    ProdutoResponseDTO produtoSalvo = service.cadastrar(dto);
    URI uri = URI.create("/produtos/");
    return ResponseEntity.created(uri).body(produtoSalvo);
  }

}
