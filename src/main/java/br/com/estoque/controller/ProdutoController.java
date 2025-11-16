package br.com.estoque.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.request.ProdutoRequestDTO;
import br.com.estoque.dto.request.ProdutoUpdateDTO;
import br.com.estoque.dto.response.ProdutoResponseDTO;
import br.com.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

  private final ProdutoService service;

  @GetMapping("/listar-produtos")
  public ResponseEntity<List<ProdutoResponseDTO>> listar() {
    List<ProdutoResponseDTO> produtos = service.listarTodosProdutos();
    return ResponseEntity.ok(produtos);
  }

  @PutMapping("/atualizar-produto/{id}")
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

  @GetMapping("/listar-quantidade-ativos")
  public ResponseEntity<Long> listarAtivos() {
    Long quantidadeProdutos = service.listarQuantidadeItensAtivos();
    return ResponseEntity.ok().body(quantidadeProdutos);
  }

  @GetMapping("/quantidade-por-categoria")
  public ResponseEntity<Map<String, Long>> listarPorCategoria() {
    return ResponseEntity.ok(service.listarProdutosPorCategoria());
  }

}
