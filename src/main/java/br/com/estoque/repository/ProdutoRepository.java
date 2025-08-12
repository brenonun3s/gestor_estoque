package br.com.estoque.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID>{

 boolean existsBySku(String sku);

@Query(
    value = "SELECT COUNT(DISTINCT p.sku) FROM produto p",
    nativeQuery = true
)
Long countDistinctSku();

 
}
