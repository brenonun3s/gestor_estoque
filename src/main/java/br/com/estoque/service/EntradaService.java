package br.com.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.mapper.EntradaMapper;
import br.com.estoque.dto.request.EntradaRequestDTO;
import br.com.estoque.dto.response.EntradaResponseDTO;
import br.com.estoque.exceptions.custom.EntradasNaoLocalizadasException;
import br.com.estoque.model.entity.Entrada;
import br.com.estoque.repository.EntradaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
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

 @Transactional(readOnly = true)
 public List<EntradaResponseDTO> listar(){
    List<Entrada> entradas = repository.findAll();

    if (entradas.isEmpty()) {
      throw new EntradasNaoLocalizadasException("Nenhuma entrada registrada.");
    }

    return entradas.stream()
        .map(mapper::toResponseDTO)
        .collect(Collectors.toList());
  }
 }

