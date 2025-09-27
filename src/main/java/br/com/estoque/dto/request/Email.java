package br.com.estoque.dto.request;

public record Email(
    String to,
    String subject,
    String body, 
    String caminhoArquivo
) {
 
}