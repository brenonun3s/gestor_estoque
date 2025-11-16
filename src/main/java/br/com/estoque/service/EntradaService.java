package br.com.estoque.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.mapper.EntradaMapper;
import br.com.estoque.dto.request.EntradaRequestDTO;
import br.com.estoque.dto.response.EntradaResponseDTO;
import br.com.estoque.exceptions.custom.EntradasNaoLocalizadasException;
import br.com.estoque.model.entity.Entrada;
import br.com.estoque.repository.EntradaRepository;

@Service
public class EntradaService {

  @Autowired
  EntradaMapper mapper;

  @Autowired
  EntradaRepository repository;

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
  public List<EntradaResponseDTO> listar() {
    List<Entrada> entradas = repository.findAll();

    if (entradas.isEmpty()) {
      throw new EntradasNaoLocalizadasException("Nenhuma entrada registrada.");
    }

    return entradas.stream()
        .map(mapper::toResponseDTO)
        .collect(Collectors.toList());
  }

  public Map<LocalDate, Integer> listarEntradasPorDia() {
    List<Object[]> resultados = repository.totalEntradasPorDia();

    return resultados.stream()
        .collect(Collectors.toMap(
            r -> ((java.sql.Date) r[0]).toLocalDate(),
            r -> ((Long) r[1]).intValue()));
  }

}
