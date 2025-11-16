package br.com.estoque.exceptions.custom;


public class EmailJaExisteException extends RuntimeException {
 public EmailJaExisteException(String message) {
  super(message);

 }
}