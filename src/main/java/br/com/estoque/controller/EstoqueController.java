package br.com.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.response.EstoqueResponseDTO;
import br.com.estoque.service.EstoqueService;


@RestController
@RequestMapping("/estoque")
public class EstoqueController {

 @Autowired
 EstoqueService service;
 
@GetMapping
public ResponseEntity<List<EstoqueResponseDTO>> listar() {
    List<EstoqueResponseDTO> estoque = service.listarQuantidadeEstoque();
    return ResponseEntity.ok().body(estoque);
}


}
