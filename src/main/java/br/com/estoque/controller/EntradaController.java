package br.com.estoque.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.EntradaRequestDTO;
import br.com.estoque.dto.EntradaResponseDTO;
import br.com.estoque.service.EntradaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/entradas")
@RequiredArgsConstructor
public class EntradaController {

 private final EntradaService service;

 @PostMapping("/registrar-entrada")
 public ResponseEntity<EntradaResponseDTO> cadastrar(@Valid @RequestBody EntradaRequestDTO dto) {
  EntradaResponseDTO entradaSalva = service.registrarEntrada(dto);
  URI uri = URI.create("/entradas/");
  return ResponseEntity.created(uri).body(entradaSalva);
 }

 @GetMapping("/listar-entradas")
 public ResponseEntity<List<EntradaResponseDTO>> listar() {
     List<EntradaResponseDTO> entradas = service.listar();
     return ResponseEntity.ok(entradas);
 }
 

}
