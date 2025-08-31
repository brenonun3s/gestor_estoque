package br.com.estoque.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice

public class GlobalExceptionHandler {

 @ExceptionHandler(ProdutoNaoLocalizadoException.class)
 public ResponseEntity<String> handleProdutoNaoLocalizadoException(ProdutoNaoLocalizadoException ex) {
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
 }

 @ExceptionHandler(ProdutoJaCadastradoException.class)
 public ResponseEntity<String> handleProdutoJaCadastradoException(ProdutoJaCadastradoException ex) {
  return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
 }

 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
  Map<String, String> errors = new HashMap<>();
  ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
  return ResponseEntity.badRequest().body(errors);
 }

 @ExceptionHandler(HttpMessageNotReadableException.class)
 public ResponseEntity<Map<String, String>> handleInvalidJson(HttpMessageNotReadableException ex) {
  Map<String, String> response = new HashMap<>();
  response.put("error", "JSON inválido ou mal formatado");
  response.put("message", ex.getMostSpecificCause().getMessage());
  return ResponseEntity.badRequest().body(response);
 }


 @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("erro", "Falha ao consultar BrasilAPI");
        body.put("detalhe", ex.getReason()); 
        body.put("status", ex.getStatusCode().value());

        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }

    @ExceptionHandler(CnpjNaoLocalicazadoException.class)
    public ResponseEntity<Map<String, Object>> handleCnpjInvalido(CnpjNaoLocalicazadoException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("erro", "CNPJ inválido");
        body.put("detalhe", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<String> handleEstoqueInsuficienteException(EstoqueInsuficienteException ex) {
     return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(EntradasNaoLocalizadasException.class)
    public ResponseEntity<String> handleEntradasNaoLocalizadasException(EntradasNaoLocalizadasException ex) {
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SaidasNaoLocalizadasException.class)
    public ResponseEntity<String> handleSaidasNaoLocalizadasException(SaidasNaoLocalizadasException ex) {
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

