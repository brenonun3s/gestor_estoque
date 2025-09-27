package br.com.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.dto.response.EstoqueResponseDTO;
import br.com.estoque.repository.EstoqueRepository;

@Service
public class EstoqueService {

  @Autowired
  EstoqueRepository repository;

  public List<EstoqueResponseDTO> listarQuantidadeEstoque(){
    List<EstoqueResponseDTO> quantidadeEstoque = repository.findAllEstoque();
    return quantidadeEstoque;

  }
}
