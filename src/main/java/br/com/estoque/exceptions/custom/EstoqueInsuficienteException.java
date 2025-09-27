package br.com.estoque.exceptions.custom;

public class EstoqueInsuficienteException extends RuntimeException {
 public EstoqueInsuficienteException(String message) {
  super(message);

 }
}