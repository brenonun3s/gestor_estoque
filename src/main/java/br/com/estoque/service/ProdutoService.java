package br.com.estoque.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.ProdutoRequestDTO;
import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.dto.ProdutoUpdateDTO;
import br.com.estoque.mapper.ProdutoMapper;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

  private final ProdutoRepository produtoRepository;

  private final ProdutoMapper produtoMapper;

  @Transactional
  public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto) {
      Produto produto = produtoMapper.toEntity(dto);
      Produto produtoSalvo = produtoRepository.save(produto);
      return produtoMapper.toResponseDTO(produtoSalvo);
  }
  
  @Transactional
  public ProdutoResponseDTO atualizarProduto(UUID id, @Valid ProdutoUpdateDTO dto) {
    try {
      Produto produtoExistente = produtoRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

      produtoMapper.updateProdutoFromDto(dto, produtoExistente);

      Produto produtoAtualizado = produtoRepository.save(produtoExistente);

      return produtoMapper.toResponseDTO(produtoAtualizado);
    } catch (RuntimeException e) {
      throw new RuntimeException("Erro ao atualizar o produto: " + e.getMessage(), e);
    }
  }

  @Transactional(readOnly = true)
  public List<ProdutoResponseDTO> listarProdutos() {
    List<Produto> produtos = produtoRepository.findAll();

    return produtos.stream().map(produtoMapper::toResponseDTO)
        .collect(Collectors.toList());

  }

  // TODO: TIVE ESSA IDEIA, ESTOU ESTUDANDO COMO FAZER -> ASSIM QUE POSSIVEL, VOU
  // IMPLEMENTAR
  public void importarProdutosViaCSV() {

  }

  @Transactional
  public void deletarProduto(UUID id) {
    try {
      Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

      produtoRepository.delete(produto);
    } catch (DataIntegrityViolationException e) {
      throw new RuntimeException("Erro ao deletar o produto: Violação de integridade referencial.", e);
    } catch (RuntimeException e) {
      throw new RuntimeException("Erro ao deletar o produto: " + e.getMessage(), e);
    }
  }

}
