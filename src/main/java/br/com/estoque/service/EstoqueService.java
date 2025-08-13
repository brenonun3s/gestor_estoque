package br.com.estoque.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueService {

 //TODO: DESENVOLVER LOGICA E PENSAR NA REGRA DE NEGOCIOSW
 public void listarEstoqueBaixo(){
  System.out.println("Produtos com estoque baixo");
 }

 //TODO: DESENVOLVER LOGICA E PENSAR NA REGRA DE NEGOCIOS
 public void listarTotalProdutosCadastrados(){
  System.out.println("Total de produtos");
}

//TODO: DESENVOLVER LOGICA E PENSAR NA REGRA DE NEGOCIOS
public void listarQuantidadeProdutosEstoqueBaixo(){
 System.out.println("Alertas de estoque baixo");
}

//TODO: DESENVOLVER LOGICA E PENSAR NA REGRA DE NEGOCIOS
public void registrarSaida(){
  System.out.println("Saida de produto do estoque");
}
 
//TODO: DESENVOLVER LOGICA E PENSAR NA REGRA DE NEGOCIOS
public void filtrarValorTotalEmEstoque(){
  System.out.println("Filtro");
}

}
