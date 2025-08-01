package br.com.estoque.model;

import java.util.ArrayList;
import java.util.UUID;

import br.com.estoque.enums.PrioridadeSolicitacao;
import br.com.estoque.enums.StatusSolicitacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Solicitacao {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 private ArrayList<Item> itens;

 private PrioridadeSolicitacao prioridade;

 private Integer quantidade;

 private String justificativa;

 private StatusSolicitacao status;

}
