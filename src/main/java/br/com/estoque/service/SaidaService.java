package br.com.estoque.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.SaidaRequestDTO;
import br.com.estoque.dto.SaidaResponseDTO;
import br.com.estoque.mapper.SaidaMapper;
import br.com.estoque.model.Saida;
import br.com.estoque.repository.SaidaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaidaService {

 private final SaidaRepository repository;

 private final SaidaMapper mapper;

 @Transactional
 public SaidaResponseDTO registrarSaida(SaidaRequestDTO dto) {
  try {
   Saida saida = mapper.toEntity(dto);
   Saida saidaSalva = repository.save(saida);   
   return mapper.toResponseDTO(saidaSalva);
  } catch (Exception e) {
   throw new RuntimeException("Erro inesperado, contate o Desenvolvedor");
  }
 }
}
