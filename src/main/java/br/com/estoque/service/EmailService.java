package br.com.estoque.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.estoque.model.Email;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

 
 private final JavaMailSender mailSender;

 public void sendEmail(Email email){
  var message = new SimpleMailMessage();
  message.setFrom("zzzbrenozzz@gmail.com");
  message.setTo(email.to());
  message.setSubject(email.subject());
  message.setText(email.body()); 
  mailSender.send(message);
 }
 
  
}
