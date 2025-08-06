package br.com.estoque.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.ProdutoRequestDTO;
import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.dto.ProdutoUpdateDTO;
import br.com.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

 private final ProdutoService service;

 @RequestMapping("/listar-produtos")
 public List<ProdutoResponseDTO> listar() {
  return service.listarProdutos();
 }

 @RequestMapping("atualizar-produto/{id}")
 public ProdutoResponseDTO atualizarProduto(@PathVariable UUID id,
   @RequestBody @Valid ProdutoUpdateDTO dto) {
  return service.atualizarProduto(id, dto);
 }

 @RequestMapping("/deletar-produto/{id}")
 public String deletarProduto(@PathVariable UUID id) {
  service.deletarProduto(id);
  return "Produto deletado com sucesso!";
 }

@PostMapping
public ResponseEntity<ProdutoResponseDTO> cadastrar(@Valid @RequestBody ProdutoRequestDTO dto) {
    ProdutoResponseDTO produtoSalvo = service.cadastrar(dto);
    URI uri = URI.create("/produtos/");
    return ResponseEntity.created(uri).body(produtoSalvo);
}

}
