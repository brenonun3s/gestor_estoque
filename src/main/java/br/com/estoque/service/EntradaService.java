package br.com.estoque.service;

import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.EntradaRequestDTO;
import br.com.estoque.dto.EntradaResponseDTO;
import br.com.estoque.mapper.EntradaMapper;
import br.com.estoque.model.Entrada;
import br.com.estoque.repository.EntradaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntradaService {

 private final EntradaMapper mapper;

 private final EntradaRepository repository;

 @Transactional
 public EntradaResponseDTO registrarEntrada(EntradaRequestDTO dto) {
  try {
   Entrada entrada = mapper.toEntity(dto);
   Entrada entradaSalva = repository.save(entrada);
   return mapper.toResponseDTO(entradaSalva);
  } catch (Exception e) {
   throw new RuntimeException("Erro inesperado, contate o Desenvolvedor");
  }
 }
}
