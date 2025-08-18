package br.com.estoque.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.estoque.dto.RelatorioProdutosDTO;
import br.com.estoque.model.Produto;

@Repository

public interface EstoqueRepository extends CrudRepository<Produto, UUID> {

 /*@Query("""
     SELECT new br.com.estoque.dto.RelatorioProdutosDTO(
         p.nome,
         p.preco,
         p.categoria,
         p.sku
     )
     FROM Produto p
 """)
 List<RelatorioProdutosDTO> gerarRelatorioProdutos();
}
  */
}
