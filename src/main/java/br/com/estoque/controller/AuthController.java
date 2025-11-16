package br.com.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.request.UserLoginDTO;
import br.com.estoque.dto.request.UserRegisterDTO;
import br.com.estoque.dto.response.TokenRefreshRequestDTO;
import br.com.estoque.dto.response.TokenResponseDTO;
import br.com.estoque.dto.response.UserResponseDTO;
import br.com.estoque.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

 @Autowired
 UsuarioService usuarioService;

 @PostMapping("/registrar")
 public UserResponseDTO register(@RequestBody @Valid UserRegisterDTO dto) {
  return usuarioService.register(dto);
 }

 @PostMapping("/login")
 public TokenResponseDTO gerarToken(@RequestBody @Valid UserLoginDTO dto) {
  String token = usuarioService.login(dto);
  return new TokenResponseDTO(token);
 }

 @PostMapping("/refresh")
 public TokenResponseDTO refreshToken(@RequestBody @Valid TokenRefreshRequestDTO dto) {
  String novoToken = usuarioService.refreshToken(dto.tokenAntigo());
  return new TokenResponseDTO(novoToken);
 }
}