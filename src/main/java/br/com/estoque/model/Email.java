package br.com.estoque.model;

public record Email(
    String to,
    String subject,
    String body, 
    String caminhoArquivo
) {
 
}
