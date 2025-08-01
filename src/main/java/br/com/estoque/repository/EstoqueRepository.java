package br.com.estoque.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, UUID>{

 
 
}
