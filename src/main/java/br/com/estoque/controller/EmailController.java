package br.com.estoque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.model.Email;
import br.com.estoque.service.EmailService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
 
 private final EmailService emailService;

 // TODO: SERÁ UM ENDPOINT PARA ENVIAR EMAILS - NECESSÁRIO CONFIGURAR O SERVIDOR DE EMAIL
 @PostMapping("/enviar")
 public ResponseEntity<String> enviarEmail(@RequestBody Email email) {
     emailService.sendEmail(email);
     return ResponseEntity.ok("Email enviado com sucesso para " + email.to());
 }
 

}
