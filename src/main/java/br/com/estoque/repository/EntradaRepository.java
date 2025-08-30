package br.com.estoque.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estoque.model.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, UUID> {
 
}
