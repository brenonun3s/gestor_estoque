package br.com.estoque.service;

import java.net.URI;
import java.time.LocalDate;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import br.com.estoque.dto.ProdutoRequestDTO;
import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.mapper.ProdutoMapper;
import br.com.estoque.model.Produto;
import br.com.estoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

 private final ProdutoRepository produtoRepository;

 private final ProdutoMapper produtoMapper;

 @Transactional
  public ResponseEntity<ProdutoResponseDTO> create(ProdutoRequestDTO dto) {
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

}
