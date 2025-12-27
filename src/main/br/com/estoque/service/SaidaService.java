package br.com.estoque.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.mapper.SaidaMapper;
import br.com.estoque.dto.request.SaidaRequestDTO;
import br.com.estoque.dto.response.SaidaResponseDTO;
import br.com.estoque.exceptions.custom.SaidasNaoLocalizadasException;
import br.com.estoque.model.entity.Saida;
import br.com.estoque.repository.SaidaRepository;


@Service
public class SaidaService {

 @Autowired
 SaidaRepository repository;

 @Autowired
 SaidaMapper mapper;

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

  public Map<LocalDate, Integer> listarSaidasPorDia() {
    List<Object[]> resultados = repository.totalSaidasPorDia();

    return resultados.stream()
            .collect(Collectors.toMap(
                    r -> ((java.sql.Date) r[0]).toLocalDate(), 
                    r -> ((Long) r[1]).intValue()
            ));
}

 }
