package br.com.estoque.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.SaidaRequestDTO;
import br.com.estoque.dto.SaidaResponseDTO;
import br.com.estoque.service.SaidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/saidas") 
@RequiredArgsConstructor
public class SaidaController {

 private final SaidaService service;
 
 @PostMapping("/registrar-saida")
 public ResponseEntity<SaidaResponseDTO> cadastrar(@Valid @RequestBody SaidaRequestDTO dto) {
  SaidaResponseDTO saidaSalva = service.registrarSaida(dto);
  URI uri = URI.create("/saidas/");
  return ResponseEntity.created(uri).body(saidaSalva);
 }

  @GetMapping("/listar-saidas")
 public ResponseEntity<List<SaidaResponseDTO>> listar() {
     List<SaidaResponseDTO> saidas = service.listar();
     return ResponseEntity.ok(saidas);
 }


}
