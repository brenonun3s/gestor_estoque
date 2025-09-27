package br.com.estoque.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estoque.dto.ProdutoResponseDTO;
import br.com.estoque.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

 boolean existsBySku(String sku);

 List<Produto> findAllByStatusTrue();

     @Query("SELECT new br.com.estoque.dto.ProdutoResponseDTO(" +
           "p.sku, p.nome, p.marca, p.quantidadeMinima, p.quantidadeMaxima, " +
           "p.preco, p.status, p.categoria, p.dataValidade) " +
           "FROM Produto p")
    List<ProdutoResponseDTO> findAllProdutosDTO();


}
