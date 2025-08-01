package br.com.estoque.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.dto.SolicitacaoResponseDTO;
import br.com.estoque.exceptions.SolicitacaoNaoLocalizadaException;
import br.com.estoque.model.Solicitacao;
import br.com.estoque.repository.SolicitacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SolicitacoService{

  @Autowired
 SolicitacaoRepository repository;

 @Transactional
 public SolicitacaoResponseDTO filtrarPorStatus(UUID id){
  try{
   Solicitacao solicitacao = repository.findById(id).orElseThrow(() -> new SolicitacaoNaoLocalizadaException("Não existem tarefas á serem listadas"));
  
   // return solicitacao.getStatus();
  }
  catch(Exception e){
   e.printStackTrace();
  }
 }
 
 //TODO: INICIADO A LOGICA DO METODO, TEM QUE CRIAR O MAPPER (NÃO FOI CRIADO POIS EXIGE UM POUCO MAIS DE ATENÇÃO)
 @Transactional
 public List<SolicitacaoResponseDTO> listar(){
  try {

    List<Solicitacao> solicitacaos = repository.findAll();
   
    return solicitacaos.stream()
    .map(mapper::toResponseDTO)
    .collect(Collectors.toList());

  } catch (Exception e) {
    e.printStackTrace();
  }
 }

 //TODO: DESENVOLVEDOR METODO E PENSAR NA LOGICA
 public SolicitacaoRequestDTO efetuarSolicitacao(Solicitacao solicitacao){
  try{

  }
 }

 
 

}
