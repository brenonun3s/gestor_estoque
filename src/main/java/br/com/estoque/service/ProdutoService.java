package br.com.estoque.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.ProdutoRequestDTO;
import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.dto.ProdutoUpdateDTO;
import br.com.estoque.exceptions.ProdutoJaCadastradoException;
import br.com.estoque.exceptions.ProdutoNaoLocalizadoException;
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
    if (dto.sku() != null && !produtoRepository.existsBySku(dto.sku())) {
      Produto produto = produtoMapper.toEntity(dto);
      Produto produtoSalvo = produtoRepository.save(produto);
      return produtoMapper.toResponseDTO(produtoSalvo);
    }
    throw new ProdutoJaCadastradoException("Produto já cadastrado com o SKU: " + dto.sku());
  }

  @Transactional
  public ProdutoResponseDTO atualizarProduto(UUID id, @Valid ProdutoUpdateDTO dto) {

    Produto produtoExistente = produtoRepository.findById(id)
        .orElseThrow(() -> new ProdutoNaoLocalizadoException("Produto não encontrado"));

    produtoMapper.updateProdutoFromDto(dto, produtoExistente);

    Produto produtoAtualizado = produtoRepository.save(produtoExistente);

    return produtoMapper.toResponseDTO(produtoAtualizado);
  }

  @Transactional(readOnly = true)
  public List<ProdutoResponseDTO> listarTodosProdutos() {
    List<Produto> produtos = produtoRepository.findAll();

    if (produtos.isEmpty()) {
      throw new ProdutoNaoLocalizadoException("Nenhum produto encontrado.");
    }

    return produtos.stream()
        .map(produtoMapper::toResponseDTO)
        .collect(Collectors.toList());
  }

  @Transactional
  public void deletarProduto(UUID id) {

    Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new ProdutoNaoLocalizadoException("Produto não encontrado com esse ID" + id));

    produtoRepository.delete(produto);

  }

  @Transactional(readOnly = true)
  public Long listarQuantidadeItensAtivos() {
    List<Produto> produtos = produtoRepository.findAllByStatusTrue();

    if (produtos.isEmpty()) {
      throw new ProdutoNaoLocalizadoException("Nenhum produto encontrado.");
    }

    Long totalQuantidade = produtos.stream()
        .count();

    return totalQuantidade;

  }

  // TODO: TIVE ESSA IDEIA, ESTOU ESTUDANDO COMO FAZER -> ASSIM QUE POSSIVEL, VOU
  // IMPLEMENTAR
  public void importarProdutosViaCSV() {

  }

}
