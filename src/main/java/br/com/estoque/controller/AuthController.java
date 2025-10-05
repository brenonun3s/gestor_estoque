package br.com.estoque.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.dto.request.UserLoginDTO;
import br.com.estoque.dto.request.UserRegisterDTO;
import br.com.estoque.dto.response.TokenRefreshRequestDTO;
import br.com.estoque.dto.response.TokenResponseDTO;
import br.com.estoque.dto.response.UserResponseDTO;
import br.com.estoque.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

 private final UserService userService;

 @PostMapping("/registrar")
 public UserResponseDTO register(@RequestBody @Valid UserRegisterDTO dto) {
  return userService.register(dto);
 }

 @PostMapping("/login")
 public TokenResponseDTO gerarToken(@RequestBody @Valid UserLoginDTO dto) {
  String token = userService.login(dto);
  return new TokenResponseDTO(token);
 }

 @PostMapping("/refresh")
 public TokenResponseDTO refreshToken(@RequestBody @Valid TokenRefreshRequestDTO dto) {
  String novoToken = userService.refreshToken(dto.tokenAntigo());
  return new TokenResponseDTO(novoToken);
 }
}