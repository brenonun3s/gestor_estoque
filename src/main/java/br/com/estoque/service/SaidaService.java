package br.com.estoque.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.mapper.SaidaMapper;
import br.com.estoque.dto.request.SaidaRequestDTO;
import br.com.estoque.dto.response.SaidaResponseDTO;
import br.com.estoque.exceptions.custom.EstoqueInsuficienteException;
import br.com.estoque.exceptions.custom.SaidasNaoLocalizadasException;
import br.com.estoque.model.entity.Produto;
import br.com.estoque.model.entity.Saida;
import br.com.estoque.repository.ProdutoRepository;
import br.com.estoque.repository.SaidaRepository;
import br.com.estoque.util.ValidadorEstoque;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SaidaService implements ValidadorEstoque {

  @Autowired
  SaidaRepository repository;

  @Autowired
  ProdutoRepository produtoRepository;

  @Autowired
  SaidaMapper mapper;

  @Transactional
  public SaidaResponseDTO registrarSaida(SaidaRequestDTO dto) {

    validarQuantidadeEstoque(dto.produtoId(), dto.quantidade());
    Saida saida = mapper.toEntity(dto);

    Produto produto = saida.getProduto();
    produto.getEstoque().setQuantidade(
        produto.getEstoque().getQuantidade() - dto.quantidade());

    Saida saidaSalva = repository.save(saida);

    return mapper.toResponseDTO(saidaSalva);
  }

  @Transactional(readOnly = true)
  public List<SaidaResponseDTO> listar() {
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
            r -> ((Long) r[1]).intValue()));
  }

  @Override
  public void validarQuantidadeEstoque(UUID produtoID, Integer quantidadeSolicitada) {
    Produto produto = produtoRepository.findById(produtoID)
        .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado no estoque."));

    Integer quantidadeDisponivel = produto.getEstoque().getQuantidade();

    if (quantidadeDisponivel < quantidadeSolicitada) {
      throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
    }
  }
}
