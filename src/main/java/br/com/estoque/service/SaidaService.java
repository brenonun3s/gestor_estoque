package br.com.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.SaidaRequestDTO;
import br.com.estoque.dto.SaidaResponseDTO;
import br.com.estoque.exceptions.SaidasNaoLocalizadasException;
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

  @Transactional(readOnly = true)
 public List<SaidaResponseDTO> listar(){
    List<Saida> saidas = repository.findAll();

    if (saidas.isEmpty()) {
      throw new SaidasNaoLocalizadasException("Nenhuma saída registrada.");
    }

    return saidas.stream()
        .map(mapper::toResponseDTO)
        .collect(Collectors.toList());
  }
 }
