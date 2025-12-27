package br.com.estoque.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.mapper.ProdutoMapper;
import br.com.estoque.dto.request.ProdutoRequestDTO;
import br.com.estoque.dto.request.ProdutoUpdateDTO;
import br.com.estoque.dto.response.ProdutoResponseDTO;
import br.com.estoque.exceptions.custom.ProdutoJaCadastradoException;
import br.com.estoque.exceptions.custom.ProdutoNaoLocalizadoException;
import br.com.estoque.exceptions.custom.ProdutoPossuiEstoqueException;
import br.com.estoque.model.entity.Produto;
import br.com.estoque.model.enums.CategoriaProdutos;
import br.com.estoque.repository.EstoqueRepository;
import br.com.estoque.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Service
public class ProdutoService {

  @Autowired
  ProdutoRepository produtoRepository;

  @Autowired
  ProdutoMapper produtoMapper;

  @Autowired
  EstoqueRepository estoqueRepository;

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
    List<ProdutoResponseDTO> produtos = produtoRepository.findAllProdutosDTO();
    return produtos;

  }

  @Transactional
  public void deletarProduto(UUID id) {

    Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new ProdutoNaoLocalizadoException("Produto não encontrado com esse ID" + id));

    boolean possuiEstoque = estoqueRepository.existsByProdutoId(id);
    if (possuiEstoque) {
      throw new ProdutoPossuiEstoqueException("Produto não pode ser excluido pois possui estoque. Inative o produto");
    }
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

  
public Map<String, Long> listarProdutosPorCategoria() {
    List<Object[]> resultados = produtoRepository.countProdutosPorCategoria();

    return resultados.stream()
            .collect(Collectors.toMap(
                    r -> ((CategoriaProdutos) r[0]).name(), 
                    r -> (Long) r[1]
            ));
}


};