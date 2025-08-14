package br.com.estoque.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

 //LOGICA COM SPRING SECURITY E JWT
 public String login(){
  return "Usuário logado com sucesso!";
 }

  //LOGICA COM SPRING SECURITY E JWT
 public String logout() {
  return "Usuário deslogado com sucesso!";
 }

 

}
