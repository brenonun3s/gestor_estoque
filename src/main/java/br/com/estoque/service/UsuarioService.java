package br.com.estoque.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

 public String login(){
  return "Usuário logado com sucesso!";
 }

 public String logout() {
  return "Usuário deslogado com sucesso!";
 }

 

}
