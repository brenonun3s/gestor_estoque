package br.com.estoque.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.estoque.dto.response.EstoqueResponseDTO;
import br.com.estoque.model.entity.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, UUID>{

     @Query("""
                SELECT new br.com.estoque.dto.response.EstoqueResponseDTO(
 p.sku,
 p.nome,
 e.quantidade
                )
                FROM Estoque e
                JOIN produto p
            """)
    List<EstoqueResponseDTO> findAllEstoque();

 
} 