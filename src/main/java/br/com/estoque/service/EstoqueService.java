package br.com.estoque.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueService {

 //TODO: DESENVOLVER LOGICA
 public void listarEstoqueBaixo(){
  System.out.println("Produtos com estoque baixo");
 }

  //TODO: DESENVOLVER LOGICA
 public void listarTotalProdutosCadastrados(){
  System.out.println("Total de produtos");
}

public void listarQuantidadeProdutosEstoqueBaixo(){
 System.out.println("Alertas de estoque baixo");
}
 
 

}
