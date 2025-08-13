package br.com.estoque.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID>{

 boolean existsBySku(String sku);

List<Produto>findAllByStatusTrue();

 
}
