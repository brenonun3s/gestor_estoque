package br.com.estoque.exceptions;

public class CnpjNaoLocalicazadoException extends RuntimeException{

 public CnpjNaoLocalicazadoException(String cnpj){
  super("O CNPJ " + cnpj + " é inválido. Verifique e tente novamente."); }

 
 
}
