package br.com.estoque.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estoque.dto.ProdutoRequestDTO;
import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.mapper.ProdutoMapper;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

 private final ProdutoRepository produtoRepository;

 private final ProdutoMapper produtoMapper;

   //TODO: ASSIM QUE IMPLEMENTADO SEGURANÇA, REFATORAR
   //TODO: METODO PODE SER MAIS ENXUTO, REFATORAR
 @Transactional
  public ResponseEntity<ProdutoResponseDTO> cadastrar(ProdutoRequestDTO dto) {
    try {
      Produto produto = produtoMapper.toEntity(dto);
      produto.setDataValidade(dto.getDataValidade());
      produto.setEstoque(dto.getEstoque());
      produto.setMarca(dto.getMarca());
      produto.setNome(dto.getNome());
      produto.setPreco(dto.getPreco());
      produto.setQuantidadeMinima(dto.getQuantidadeMinima());
      produto.setSku(dto.getSku());
      produto.setStatus(dto.getStatus());

      Produto produtoSalvo = produtoRepository.save(produto);

      ProdutoResponseDTO responseDTO = produtoMapper.toResponseDTO(produtoSalvo);

      URI uri = URI.create("/produtos/" + produtoSalvo.getId());
      
      return ResponseEntity.created(uri).body(responseDTO);

  }
  catch(Exception e){
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }


  }

  //TODO: ASSIM QUE IMPLEMENTADO SEGURANÇA, REFATORAR
  @Transactional(readOnly = true)
  public List<ProdutoResponseDTO> listarProdutos(){
    List<Produto> produtos = produtoRepository.findAll();

    return produtos.stream().map(produtoMapper::toResponseDTO)
    .collect(Collectors.toList());
    
  }

  //TODO: PENDENTE -> DESENVOLVER A LOGICA DESSE METODO
  @Transactional
  public ProdutoResponseDTO atualizarProduto(){
    try {
      
    } catch (Exception e) {

    }
  }

  //TODO: TIVE ESSA IDEIA, ESTOU ESTUDANDO COMO FAZER -> ASSIM QUE POSSIVEL, VOU IMPLEMENTAR
  public void importarProdutosViaCSV(){

  }

  //TODO: DESENVOLVER LOGICA
  public void deletarProduto(){

  }



}
