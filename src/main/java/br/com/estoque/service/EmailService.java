package br.com.estoque.service;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.estoque.dto.request.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

 private final JavaMailSender mailSender;

 public void sendEmail(Email email) {
  var message = new SimpleMailMessage();
  message.setFrom("zzzbrenozzz@gmail.com");
  message.setTo(email.to());
  message.setSubject(email.subject());
  message.setText(email.body());
  mailSender.send(message);
 }

 public void enviarEmailComAnexo(Email email
) throws MessagingException {

  MimeMessage mimeMessage = mailSender.createMimeMessage();

  // true = multipart (para permitir anexos)
  MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
  helper.setTo(email.to());
  helper.setSubject(email.subject());
  helper.setText(email.body(), false); // false = texto simples (true para HTML)

  // Anexar arquivo
  FileSystemResource file = new FileSystemResource(new File(email.caminhoArquivo()));
  helper.addAttachment(file.getFilename(), file);

  mailSender.send(mimeMessage);
 }
}
