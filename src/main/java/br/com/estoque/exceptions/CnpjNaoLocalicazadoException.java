package br.com.estoque.exceptions;

public class CnpjNaoLocalicazadoException extends RuntimeException{

 public CnpjNaoLocalicazadoException(String cnpj){
  super("O CNPJ " + cnpj + " não foi encontrado. Verifique os dados e tente novamente."); }

 
 
}
